import modules.Food;
import modules.Message;
import modules.response.FoodsResponse;
import utils.kafka.MessageReceiver;
import utils.kafka.MessageSender;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class AvroTest {

    public static void Fun(Object response){

        System.out.println(response);

    }


    public static void main(String[] args) {

        Food food = new Food("菜品名", 66.51);
        FoodsResponse foods = new FoodsResponse(food);

        /*
            sender and receiver
         */
//        Sender producer = new Sender("dodlee.cn:9092");
//        Receiver consumer = new Receiver("dodlee.cn:9092", Message.getSchemaByClass(FoodsResponse.class), Message.getTopicByClass(FoodsResponse.class));

        MessageSender producer = new MessageSender("dodlee.cn:9092");

        MessageReceiver consumer = new MessageReceiver("dodlee.cn:9092", FoodsResponse.class);


        //send and receive process
        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            producer.produce(foods);

            //add new food
            foods.getFoods().add(new Food("菜品名", new Random().nextInt(32)));

            List<? extends Message> records = consumer.receiveMessage();
            if (records.size() != 0) {
                records.forEach(new Consumer<Message>() {
                    @Override
                    public void accept(Message message) {
                        System.out.println(message.getTopic());
                    }
                });
            }
        }



    }


}
