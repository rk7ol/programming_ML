package handlers;

import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.request.RegisterFoodRequest;
import modules.response.RegisterFoodResponse;

public class RegisterFoodRequestHandler extends MessageHandler<RegisterFoodRequest> {
    @Override
    public boolean handle(RegisterFoodRequest message) {
        int flag = 0;
        //flag = RegisterFood(message);数据库写入
        MessageManager.sendMessage(new RegisterFoodResponse(flag));
        return false;
    }
}


