package handlers;

import flow.handlers.MessageHandler;
import modules.response.AddFoodsResponse;

public class AddFoodsResponseHandler extends MessageHandler<AddFoodsResponse> {
    @Override
    public boolean handle(AddFoodsResponse message) {
        return false;
    }
}
