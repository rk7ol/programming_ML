package handlers;

import flow.handlers.MessageHandler;
import modules.response.SellFoodResponse;
import send.Dispatcher;

//菜品交易，返回价格结算
public class SellFoodResponseHandler extends MessageHandler<SellFoodResponse> {
    @Override
    public boolean handle(SellFoodResponse message) {
        double price=message.getTotallyPrice();

        Dispatcher.saveResponse(message.getSession(), price);
        return true;
    }
}
