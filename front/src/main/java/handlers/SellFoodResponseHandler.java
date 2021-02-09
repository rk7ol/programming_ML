package handlers;

import flow.handlers.MessageHandler;
import modules.response.SellFoodResponse;

public class SellFoodResponseHandler extends MessageHandler<SellFoodResponse> {
    @Override
    public boolean handle(SellFoodResponse message) {
        return false;
    }
}
