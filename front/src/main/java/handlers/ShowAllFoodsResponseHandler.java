package handlers;

import flow.handlers.MessageHandler;
import modules.response.ShowAllFoodsResponse;

public class ShowAllFoodsResponseHandler extends MessageHandler<ShowAllFoodsResponse> {
    @Override
    public boolean handle(ShowAllFoodsResponse message) {
        return false;
    }
}

