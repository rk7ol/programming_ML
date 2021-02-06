package send;
import flow.MessageManager;

import handlers.RegisterFoodResponseHandler;
import org.apache.avro.Protocol;

public class send {
    public static void sendRegisterFoodRequest(String ID, String name, String method, String price){
        MessageManager.sendMessage(new RegisterFoodRequest(ID,name,method,Double.parseDouble(price));
    }
}
