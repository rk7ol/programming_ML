package handlers;

import flow.handlers.MessageHandler;
import modules.request.AddFoodsRequest;

public class AddFoodsRequestHandler extends MessageHandler<AddFoodsRequest> {
    @Override
    public boolean handle(AddFoodsRequest message) {
        return false;
    }
}
