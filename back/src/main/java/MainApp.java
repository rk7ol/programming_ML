import flow.MessageManager;
import handlers.FoodsRequestHandler;
import handlers.RegisterFoodRequestHandler;
import modules.Request;
import modules.request.FoodsRequest;
import modules.request.RegisterFoodRequest;

public class MainApp {

    public static void main(String[] args) {

        MessageManager<Request> messageManager = new MessageManager<>(FoodsRequest.class);

        new Thread(new Runnable() {
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

        //messageManager.addHandler(FoodsRequest.class, new FoodsRequestHandler());

        messageManager.addHandler(RegisterFoodRequest.class,new RegisterFoodRequestHandler());

        messageManager.start();









    }
}
