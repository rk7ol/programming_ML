package handlers;

import flow.handlers.MessageHandler;
import modules.response.SettleResponse;

public class SettleResponseHandler extends MessageHandler<SettleResponse> {
    @Override
    public boolean handle(SettleResponse message) {
        return false;
    }
}
