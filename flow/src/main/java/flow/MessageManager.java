package flow;

import flow.handlers.MessageHandler;
import modules.Message;
import utils.kafka.MessageReceiver;
import utils.kafka.MessageSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessageManager<T extends Message> {

    protected static LinkedList<Message> receiveBuffer;
    protected static LinkedList<Message> sendBuffer;

    protected MessageSender sender;
    protected ArrayList<MessageReceiver> receivers;
    protected ArrayList<Class<? extends Message>> types;

    private final AtomicBoolean isRunning = new AtomicBoolean(true);


    public MessageManager() {
        sendBuffer = new LinkedList<>();
        receiveBuffer = new LinkedList<>();
        sender = new MessageSender("dodlee.cn:9092");

        receivers = new ArrayList<>();
        this.types = new ArrayList<>();
    }


    /**
     * create message manager with pre register message types
     *
     * @param types message types
     */
    public MessageManager(Class<? extends T>... types) {
        this();





        for (Class<? extends T> type : types) {
            addNewReceiver(type);
        }

        this.types.addAll(Arrays.asList(types));
    }


    private final Runnable sendMessageTask = new Runnable() {
        @Override
        public void run() {
            while (isRunning.get()) {
                if (!sendBuffer.isEmpty())
                    sender.produce(sendBuffer.poll());
            }

        }
    };

    private final Runnable handleMessageTask = new Runnable() {
        @Override
        public void run() {
            while (isRunning.get()) {

                if (!receiveBuffer.isEmpty()) {

                    Message message = receiveBuffer.poll();
                    MessageHandler<? extends Message> handler = getHandler(message.getClass());

                    if (handler == null) {
                        //no handler
                        System.out.println("message:<" + message.getClass() + "> handler not register");
                    } else {
                        //do handle
                        handler.doHandle(message);
                    }

                }


            }

        }
    };


    public void start() {

        //send start
        new Thread(sendMessageTask).start();

        //receive start
        for (MessageReceiver receiver : receivers) {
            receiverStart(receiver);
        }

        //handle start
        new Thread(handleMessageTask).start();

    }

    private void receiverStart(MessageReceiver receiver) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning.get()) {
                    receiveBuffer.addAll(receiver.receiveMessage());
                }
            }
        }).start();
    }

    public void stop() {
        isRunning.set(false);
    }


    protected HashMap<Class<? extends Message>, MessageHandler<? extends T>> HandlerMap = new HashMap<>();


    public static void sendMessage(Message message) {
        sendBuffer.add(message);
    }


    /**
     * @param type    type to handle
     * @param handler message deal handler
     */
    public void addHandler(Class<? extends T> type, MessageHandler<? extends T> handler) {

        if (!types.contains(type)) {
            types.add(type);

            //add new receiver
            addNewReceiver(type);
        }

        HandlerMap.put(type, handler);
    }

    private void addNewReceiver(Class<? extends T> type) {
        receivers.add(new MessageReceiver("dodlee.cn:9092", type));
    }


    private MessageHandler<? extends T> getHandler(Class<? extends Message> type) {
        return HandlerMap.get(type);
    }


}
