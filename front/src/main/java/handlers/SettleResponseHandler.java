package handlers;

import flow.handlers.MessageHandler;
import modules.response.SettleResponse;

//营业额结算，返回总盈利
public class SettleResponseHandler extends MessageHandler<SettleResponse> {
    @Override
    public boolean handle(SettleResponse message) {
        double profit=message.getProfit();
        return true;
    }
}
