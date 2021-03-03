package handlers;

import flow.handlers.MessageHandler;
import modules.response.RegisterWindowResponse;
import send.Dispatcher;

//注册卡机，返回卡机ID
public class RegisterWindowResponseHandler extends MessageHandler<RegisterWindowResponse> {
    @Override
    public boolean handle(RegisterWindowResponse message) {
        String ID=message.getID();
        Dispatcher.saveResponse(message.getSession(), ID);
        return true;
    }
}
