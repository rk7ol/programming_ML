package handlers;

import flow.handlers.MessageHandler;
import modules.response.RegisterFoodResponse;

public class RegisterFoodResponseHandler extends MessageHandler<RegisterFoodResponse> {
    @Override
    public boolean handle(RegisterFoodResponse message) {
        int flag=message.getFlag();
        if(flag==1)
            System.out.println("添加成功！");
        return false;
    }
}
