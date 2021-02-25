package handlers;

import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.Food;
import modules.request.ShowWindowFoodsRequest;
import modules.response.ShowWindowFoodsResponse;

import java.util.List;

public class ShowWindowFoodsRequestHandler extends MessageHandler<ShowWindowFoodsRequest> {

    Food DBShowWindowFoods(String WindowID) {
        List<Food> foods=null;
        return foods;
    }
    @Override
    public boolean handle(ShowWindowFoodsRequest message) {
        Food foods=DBShowWindowFoods(message.getID());
        MessageManager.sendMessage(new ShowWindowFoodsResponse(foods));
        return true;
    }
}
