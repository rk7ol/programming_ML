package handlers;

import flow.handlers.MessageHandler;
import modules.response.RegisterFoodResponse;
import send.*;

//注册菜品到服务器，返回‘0’失败，返回‘1’为成功。
public class RegisterFoodResponseHandler extends MessageHandler<RegisterFoodResponse> {
    @Override
    public boolean handle(RegisterFoodResponse message) {
        boolean flag=message.getFlag();

        Dispatcher.saveResponse(message.getSession(), flag);
        return true;
    }
}
