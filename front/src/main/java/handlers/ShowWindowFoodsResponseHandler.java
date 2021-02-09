package handlers;

import flow.handlers.MessageHandler;
import modules.response.ShowWindowFoodsResponse;

public class ShowWindowFoodsResponseHandler extends MessageHandler<ShowWindowFoodsResponse> {
    @Override
    public boolean handle(ShowWindowFoodsResponse message) {
        return false;
    }
}
