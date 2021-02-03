package handlers;

import flow.handlers.MessageHandler;
import modules.response.RegisterFoodResponse;

public class RegisterFoodResponseHandler extends MessageHandler<RegisterFoodResponse> {
    @Override
    public boolean handle(RegisterFoodResponse message) {
        return false;
    }
}
