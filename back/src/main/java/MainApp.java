import flow.MessageManager;
import handlers.FoodsRequestHandler;
import modules.Request;
import modules.request.FoodsRequest;

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

        messageManager.addHandler(FoodsRequest.class, new FoodsRequestHandler());

        messageManager.start();









    }
}
