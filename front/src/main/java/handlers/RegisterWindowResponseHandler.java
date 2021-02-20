package handlers;

import flow.handlers.MessageHandler;
import modules.response.RegisterWindowResponse;

//注册卡机，返回卡机ID
public class RegisterWindowResponseHandler extends MessageHandler<RegisterWindowResponse> {
    @Override
    public boolean handle(RegisterWindowResponse message) {
        String ID=message.getID();
        return true;
    }
}
