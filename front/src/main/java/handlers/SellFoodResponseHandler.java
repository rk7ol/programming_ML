package handlers;

import flow.handlers.MessageHandler;
import modules.response.SellFoodResponse;

//菜品交易，返回价格结算
public class SellFoodResponseHandler extends MessageHandler<SellFoodResponse> {
    @Override
    public boolean handle(SellFoodResponse message) {
        double price=message.getFlag();
        return true;
    }
}