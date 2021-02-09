package handlers;

import flow.handlers.MessageHandler;
import modules.Food;
import modules.response.ShowAllFoodsResponse;
import java.util.List;

//显示所有菜品，返回所有菜品
public class ShowAllFoodsResponseHandler extends MessageHandler<ShowAllFoodsResponse> {
    @Override
    public boolean handle(ShowAllFoodsResponse message) {
       List<Food> foods=message.getFoods();
        return true;
    }
}

