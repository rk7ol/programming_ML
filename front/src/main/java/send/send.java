package send;
import flow.MessageManager;
import modules.request.RegisterFoodRequest;


public class send {
    public static void sendRegisterFoodRequest(String name, String method, String price){
        MessageManager.sendMessage(new RegisterFoodRequest(name,method,Double.parseDouble(price)));
    }
}
