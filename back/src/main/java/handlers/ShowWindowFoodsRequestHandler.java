package handlers;

import Database.OP.KC_Table;
import Database.OV.KC_OV;
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
        List<KC_OV> kc_ovList;
        try {
            KC_Table kc_table=new KC_Table();
            kc_ovList=kc_table.select(message.getID());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Food foods=DBShowWindowFoods(message.getID());
        MessageManager.sendMessage(new ShowWindowFoodsResponse(foods));
        return true;
    }
}
