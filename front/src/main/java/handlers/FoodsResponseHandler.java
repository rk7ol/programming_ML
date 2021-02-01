package handlers;

import flow.handlers.MessageHandler;
import modules.response.FoodsResponse;

public class FoodsResponseHandler extends MessageHandler<FoodsResponse> {

    @Override
    public boolean handle(FoodsResponse message) {
        System.out.println(message.getFoods().size());
        return false;
    }
}
