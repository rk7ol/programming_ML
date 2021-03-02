import flow.MessageManager;
import handlers.*;
import modules.Request;
import modules.request.*;

public class MainApp {

    public static void main(String[] args) {

        MessageManager<Request> messageManager = new MessageManager<Request>();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MessageManager.sendMessage(new FoodsRequest("1234"));
                }

            }
        }).start();
        */

        messageManager.addHandler(AddFoodsRequest.class, new AddFoodsRequestHandler());

        messageManager.addHandler(RegisterFoodRequest.class,new RegisterFoodRequestHandler());

        messageManager.addHandler(RegisterWindowRequest.class,new RegisterWindowRequestHandler());

        messageManager.addHandler(SellFoodRequest.class,new SellFoodRequestHandler());

        messageManager.addHandler(SettleRequest.class,new SettleRequestHandler());

        messageManager.addHandler(ShowAllFoodsRequest.class,new ShowAllFoodRequestHandler());

        messageManager.addHandler(ShowWindowFoodsRequest.class,new ShowWindowFoodsRequestHandler());

        messageManager.start();

    }
}
