package send;

import flow.MessageManager;
import modules.Food;
import modules.Request;
import modules.request.AddFoodsRequest;
import modules.request.RegisterFoodRequest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import send.sessions.BooleanSession;
import send.sessions.Session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Dispatcher {


    private static final Jedis jedis = new Jedis("dodlee.cn", 6370);

    public interface Callback<T extends Serializable> {
        void call(T result);
    }

    public static void saveResponse(String sessionID, boolean value) {

        saveResponseByBytes(sessionID, new byte[]{(byte) (value ? 1 : 0)});

    }

    private static void saveResponseByBytes(String sessionID, byte[] bytes) {

        try {

            jedis.set(sessionID.getBytes(), bytes);
        } catch (JedisConnectionException e) {
            System.err.println(e.getMessage());
        }


    }


    private static class RequestSender<T extends Serializable> {


        public void sendRequest(Request request, Session<T> session, Callback<T> callback) {


            MessageManager.sendMessage(request);

            while (true) {

                setSessionValue(session);

                if (isSessionDone(session)) {
                    callback.call(session.getValue());


                    if (destroySession(session)) {
                        break;
                    } else {
                        throw new IllegalStateException("session destroy failed");
                    }

                }


            }
        }


        /**
         * save session
         *
         * @param session
         * @return
         */
        private static boolean startSession(Session<? extends Serializable> session) {
            if (isSessionExist(session)) {
                throw new IllegalStateException("session exist");
            } else {
                jedis.set(session.getID().getBytes(), null);
                return isSessionExist(session);
            }
        }

        /**
         * get session value
         *
         * @param session
         */
        public static void setSessionValue(Session<? extends Serializable> session) {
            byte[] bytes = jedis.get(session.getID().getBytes());


            if (bytes != null)
                session.setValue(bytes);

        }

        private static boolean isSessionDone(Session<? extends Serializable> session) {

            return session.getValue() != null;
        }

        private static boolean destroySession(Session<? extends Serializable> session) {

            jedis.del(session.getID().getBytes());

            return !isSessionExist(session);
        }

        private static Boolean isSessionExist(Session<? extends Serializable> session) {
            return jedis.exists(session.getID().getBytes());
        }

    }


    private static final Map<Class<? extends Request>, RequestSender<? extends Serializable>> senderMap;


    //add request sender
    static {
        senderMap = new HashMap<>();

        senderMap.put(AddFoodsRequest.class, new RequestSender<Boolean>());

        senderMap.put(RegisterFoodRequest.class, new RequestSender<Boolean>());

    }


    private static RequestSender<? extends Serializable> getRequestSender(Class<? extends Request> requestClass) {
        return senderMap.get(requestClass);
    }


    //从服务器中选择已有菜品进行添加
    public static void sendAddFoodsRequest(Callback<Boolean> callback, String ID, Food... foods) {

        RequestSender<Boolean> sender = (RequestSender<Boolean>) getRequestSender(AddFoodsRequest.class);


        BooleanSession session = new BooleanSession();

        sender.sendRequest(new AddFoodsRequest(session.getID(), ID, foods), session, callback);


    }

    //注册菜品到服务器
    public static void sendRegisterFoodRequest(Callback<Boolean> callback, String name, String method, String price) {

        RequestSender<Boolean> sender = (RequestSender<Boolean>) getRequestSender(RegisterFoodRequest.class);

        BooleanSession session = new BooleanSession();

        sender.sendRequest(new RegisterFoodRequest(session.getID(), name, method, Double.parseDouble(price)), session, callback);


    }

    //注册卡机
    public static void sendRegisterWindowRequest(Food... foods) {
        //MessageManager.sendMessage(new RegisterWindowRequest(foods));
    }

    //菜品交易，服务器进行结算
    public static void sendSellFoodRequest(String ID, Food... foods) {
        // MessageManager.sendMessage(new SellFoodRequest(ID, foods));
    }

    //营业额结算
    public static void sendRegisterSettleRequest(String ID) {
        //MessageManager.sendMessage(new SettleRequest(ID));
    }

    //显示所有菜品
    public static void sendShowAllFoodsRequest() {
        //MessageManager.sendMessage(new ShowAllFoodsRequest());
    }

    //显示卡机菜品
    public static void sendShowAllWindowFoodsRequest(String ID) {
        //MessageManager.sendMessage(new ShowWindowFoodsRequest(ID));
    }
}
