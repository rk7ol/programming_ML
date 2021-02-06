package send;
import flow.MessageManager;
import modules.request.FoodsRequest;
import modules.request.RegisterFoodRequest;


public class send {
    public static void sendRegisterFoodRequest(String ID, String name, String method, String price){
        MessageManager.sendMessage(new RegisterFoodRequest(ID,name,method,Double.parseDouble(price)));
    }
}
