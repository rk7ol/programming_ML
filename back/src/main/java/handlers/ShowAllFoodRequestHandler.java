package handlers;

import Database.OP.CP_Table;
import Database.OV.CP_OV;
import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.Food;
import modules.request.ShowAllFoodsRequest;
import modules.response.ShowWindowFoodsResponse;

import java.util.List;

public class ShowAllFoodRequestHandler extends MessageHandler<ShowAllFoodsRequest> {
    @Override
    public boolean handle(ShowAllFoodsRequest message) {
        try {
            CP_Table cp_table=new CP_Table();
            List<CP_OV> cp_ovList=cp_table.select();
            List<Food> foodList=null;
            for (int i = 0; i < cp_ovList.size(); i++) {
                CP_OV cp_ov=cp_ovList.get(i);
                Food food=new Food(cp_ov.getID(),cp_ov.getName(),cp_ov.getPrice(),cp_ov.getWay());
                foodList.add(food);
            }
            Food[] foods=new Food[foodList.size()];
            for (int i = 0; i < foodList.size(); i++) {
                foods[i]=foodList.get(i);
            }
            MessageManager.sendMessage(new ShowWindowFoodsResponse(message.getSession(),foods));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
