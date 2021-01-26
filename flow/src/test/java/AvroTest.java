import modules.Food;
import modules.Foods;
import org.apache.avro.generic.GenericRecord;
import utils.kafka.MessageReceiver;
import utils.kafka.MessageSender;

import java.util.List;
import java.util.Random;

public class AvroTest {

    public static String FOODS_TOPIC = "Foods";


    public static void main(String[] args) {

        Food food = new Food("菜品名", 66.51);
        Foods foods = new Foods(food);

        /*
            sender and receiver
         */
        MessageSender producer = new MessageSender("dodlee.cn:9092");
        MessageReceiver consumer = new MessageReceiver("dodlee.cn:9092", Foods.FOODS_SCHEMA, FOODS_TOPIC);


        //send and receive process
        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            producer.produce(foods, Foods.FOODS_SCHEMA, FOODS_TOPIC);

            //add new food
            foods.getFoods().add(new Food("菜品名", new Random().nextInt(32)));

            List<GenericRecord> records = consumer.receive();
            if (records.size() != 0) {
                records.forEach(System.out::println);
            }
        }

    }


}
