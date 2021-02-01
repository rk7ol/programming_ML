import flow.MessageManager;
import handlers.FoodsResponseHandler;
import modules.Food;
import modules.Response;
import modules.response.FoodsResponse;

import java.util.Random;

public class MainApp {

    public static void main(String[] args) {

        MessageManager<Response> messageManager = new MessageManager<>(FoodsResponse.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //create response
                    int count = new Random().nextInt(5) + 1;
                    FoodsResponse foodsResponse = new FoodsResponse();
                    for (int i = 0; i < count; i++)
                        foodsResponse.getFoods().add(new Food("菜品名", 48));

                    //send message
                    MessageManager.sendMessage(foodsResponse);
                }

            }
        }).start();

        messageManager.addHandler(FoodsResponse.class, new FoodsResponseHandler());

        messageManager.start();


    }
}
