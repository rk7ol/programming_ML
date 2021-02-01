package flow;

import flow.handlers.MessageHandler;
import modules.Message;
import utils.kafka.MessageReceiver;
import utils.kafka.MessageSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessageManager<T extends Message> {

    protected static LinkedList<Message> receiveBuffer;
    protected static LinkedList<Message> sendBuffer;

    protected MessageSender sender;
    protected ArrayList<MessageReceiver> receivers;

    private AtomicBoolean isRunning = new AtomicBoolean(true);


    private Runnable sendMessageTask = new Runnable() {
        @Override
        public void run() {
            while (isRunning.get()) {
                if (!sendBuffer.isEmpty())
                    sender.produce(sendBuffer.poll());
            }

        }
    };

    private Runnable handleMessageTask = new Runnable() {
        @Override
        public void run() {
            while (isRunning.get()) {

                if (!receiveBuffer.isEmpty()) {

                    Message message = receiveBuffer.poll();
                    MessageHandler<? extends Message> handler = getHandler(message.getClass());

                    handler.doHandle(message);
                }


            }

        }
    };


    public MessageManager(Class<? extends T>... types) {

        sendBuffer = new LinkedList<>();
        receiveBuffer = new LinkedList<>();

        sender = new MessageSender("dodlee.cn:9092");
        receivers = new ArrayList<>(types.length);

        for (Class<? extends T> type : types) {
            receivers.add(new MessageReceiver("dodlee.cn:9092", type));
        }
    }


    public void start() {

        new Thread(sendMessageTask).start();

        for (MessageReceiver receiver : receivers) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning.get()) {
                        receiveBuffer.addAll(receiver.receiveMessage());
                    }
                }
            }).start();
        }


        new Thread(handleMessageTask).start();

    }

    public void stop() {
        isRunning.set(false);
    }


    protected HashMap<Class<? extends Message>, MessageHandler<? extends T>> HandlerMap = new HashMap<>();


    public static void sendMessage(Message message) {
        sendBuffer.add(message);
    }


    /**
     *
     * @param type type to handle
     * @param handler
     */
    public void addHandler(Class<? extends T> type, MessageHandler<? extends T> handler) {
        HandlerMap.put(type, handler);
    }


    private MessageHandler<? extends T> getHandler(Class<? extends Message> type) {
        return HandlerMap.get(type);
    }


}
