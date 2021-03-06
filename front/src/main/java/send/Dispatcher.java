package send;

import flow.MessageManager;
import modules.Food;
import modules.Foods;
import modules.Request;
import modules.request.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import send.sessions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Dispatcher {


    private static final Jedis jedis = new Jedis("dodlee.cn", 6370);

    public interface Callback<T extends Serializable> {
        void call(T result);
    }

    private static Jedis getJedisConnect(String host, int port){
        return new Jedis(host, port);
    }

    public static void saveResponse(String sessionID, String value) {

        saveResponseByBytes(sessionID, value.getBytes(StandardCharsets.UTF_8));

    }

    public static void saveResponse(String sessionID, double value) {

        objectSerialize(sessionID, value);


    }


    public static void saveResponse(String sessionID, List<Food> value) {

        saveResponse(sessionID, new Foods(value));

    }


    public static void saveResponse(String sessionID, Foods value) {

        objectSerialize(sessionID, value);

    }

    public static void saveResponse(String sessionID, boolean value) {

        saveResponseByBytes(sessionID, new byte[]{(byte) (value ? 1 : 0)});

    }

    private static void objectSerialize(String sessionID, Serializable value) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

            objectOutputStream.writeObject(value);

            saveResponseByBytes(sessionID, out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
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


            Jedis jedis = getJedisConnect("dodlee.cn", 6370);

            while (true) {

                setSessionValue(jedis, session);

                if (isSessionDone(session)) {
                    callback.call(session.getValue());


                    if (destroySession(jedis, session)) {
                        break;
                    } else {
                        throw new IllegalStateException("session destroy failed");
                    }

                }


            }
        }


        /**
         * get session value
         *
         * @param session
         */
        public static void setSessionValue(Jedis jedis, Session<? extends Serializable> session) {
            byte[] bytes = jedis.get(session.getID().getBytes());


            if (bytes != null)
                session.setValue(bytes);

        }

        private static boolean isSessionDone(Session<? extends Serializable> session) {

            return session.getValue() != null;
        }

        private static boolean destroySession(Jedis jedis, Session<? extends Serializable> session) {

            jedis.del(session.getID().getBytes());

            return !isSessionExist(jedis, session);
        }

        private static Boolean isSessionExist(Jedis jedis, Session<? extends Serializable> session) {
            return jedis.exists(session.getID().getBytes());
        }

    }


    private static final Map<Class<? extends Request>, RequestSender<? extends Serializable>> senderMap;


    //add request sender
    static {
        senderMap = new HashMap<>();

        senderMap.put(RegisterFoodRequest.class, new RequestSender<Boolean>());
        senderMap.put(AddFoodsRequest.class, new RequestSender<Boolean>());
        senderMap.put(RegisterWindowRequest.class, new RequestSender<String>());
        senderMap.put(ShowWindowFoodsRequest.class, new RequestSender<Foods>());
        senderMap.put(ShowAllFoodsRequest.class, new RequestSender<Foods>());
        senderMap.put(SellFoodRequest.class, new RequestSender<Double>());
        senderMap.put(SettleRequest.class, new RequestSender<Double>());
        senderMap.put(DeleteFoodsRequest.class, new RequestSender<Boolean>());
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
    public static void sendRegisterWindowRequest(Callback<String> callback, Food... foods) {

        RequestSender<String> sender = (RequestSender<String>) getRequestSender(RegisterWindowRequest.class);

        StringSession session = new StringSession();

        sender.sendRequest(new RegisterWindowRequest(session.getID(), foods), session, callback);


    }

    //菜品交易，服务器进行结算
    public static void sendSellFoodRequest(Callback<Double> callback, String ID, Food... foods) {
        RequestSender<Double> sender = (RequestSender<Double>) getRequestSender(SellFoodRequest.class);

        DoubleSession session = new DoubleSession();

        sender.sendRequest(new SellFoodRequest(session.getID(), ID, foods), session, callback);
    }

    //营业额结算
    public static void sendSettleRequest(Callback<Double> callback, String ID) {
        RequestSender<Double> sender = (RequestSender<Double>) getRequestSender(SettleRequest.class);

        DoubleSession session = new DoubleSession();

        sender.sendRequest(new SettleRequest(session.getID(), ID), session, callback);
    }

    //显示所有菜品
    public static void sendShowAllFoodsRequest(Callback<Foods> callback) {
        RequestSender<Foods> sender = (RequestSender<Foods>) getRequestSender(ShowAllFoodsRequest.class);

        FoodsSession session = new FoodsSession();

        sender.sendRequest(new ShowAllFoodsRequest(session.getID()), session, callback);
    }

    //显示卡机菜品
    public static void sendShowWindowFoodsRequest(Callback<Foods> callback, String ID) {
        RequestSender<Foods> sender = (RequestSender<Foods>) getRequestSender(ShowWindowFoodsRequest.class);

        FoodsSession session = new FoodsSession();

        sender.sendRequest(new ShowWindowFoodsRequest(session.getID(), ID), session, callback);
    }

    //显示卡机菜品
    public static void sendDeleteFoodsRequest(Callback<Boolean> callback, Food... foods) {
        RequestSender<Boolean> sender = (RequestSender<Boolean>) getRequestSender(DeleteFoodsRequest.class);

        BooleanSession session = new BooleanSession();

        sender.sendRequest(new DeleteFoodsRequest(session.getID(), foods), session, callback);
    }
}
