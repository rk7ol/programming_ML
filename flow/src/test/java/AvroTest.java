import modules.Food;
import modules.Message;
import modules.request.AddFoodsRequest;
import modules.response.AddFoodsResponse;
import utils.kafka.MessageReceiver;
import utils.kafka.MessageSender;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class AvroTest {

    public static void Fun(Object response) {

        System.out.println(response);

    }

    private static class MessageTester<T extends Message> {


        LinkedList<T> messageList = new LinkedList<>();


        public void test() {
            MessageSender producer = new MessageSender("dodlee.cn:9092");

            MessageReceiver consumer = new MessageReceiver("dodlee.cn:9092", messageList.getFirst().getClass());


            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        messageList.forEach(producer::produce);

                        List<? extends Message> records = consumer.receiveMessage();
                        if (records.size() != 0) {
                            records.forEach(new Consumer<Message>() {
                                @Override
                                public void accept(Message message) {
                                    System.out.println(message);
                                }
                            });
                        }
                    }
                }
            }).start();
            //send and receive process

        }

        public MessageTester(T... messages) {
            messageList.addAll(Arrays.asList(messages));

        }


    }


    public static void main(String[] args) {

        //MessageTester<AddFoodsRequest> addFoodsRequestMessageTester = new MessageTester<>(new AddFoodsRequest("dw", new Food("s", 14)));

        //addFoodsRequestMessageTester.test();

       // MessageTester<AddFoodsResponse> addFoodsResponseMessageTester = new MessageTester<>(new AddFoodsResponse(true));

       // addFoodsResponseMessageTester.test();


    }


}
