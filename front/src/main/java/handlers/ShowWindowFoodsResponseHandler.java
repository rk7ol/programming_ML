package handlers;

import flow.handlers.MessageHandler;
import modules.Food;
import modules.response.ShowWindowFoodsResponse;
import send.Dispatcher;

import java.util.List;

//显示卡机菜品，返回菜品
public class ShowWindowFoodsResponseHandler extends MessageHandler<ShowWindowFoodsResponse> {
    @Override
    public boolean handle(ShowWindowFoodsResponse message) {
        List<Food> foods=message.getFoods();
        Dispatcher.saveResponse(message.getSession(), foods);
        return true;
    }
}
