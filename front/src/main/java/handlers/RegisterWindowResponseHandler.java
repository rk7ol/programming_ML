package handlers;

import flow.handlers.MessageHandler;
import modules.response.RegisterWindowResponse;

public class RegisterWindowResponseHandler extends MessageHandler<RegisterWindowResponse> {
    @Override
    public boolean handle(RegisterWindowResponse message) {
        return false;
    }
}
