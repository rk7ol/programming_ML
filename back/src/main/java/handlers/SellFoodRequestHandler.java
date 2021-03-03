package handlers;

import Database.OP.CP_Table;
import Database.OP.LS_Table;
import Database.OV.CP_OV;
import Database.OV.LS_OV;
import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.Food;
import modules.request.SellFoodRequest;
import modules.response.SellFoodResponse;

import java.util.List;

import static handlers.RegisterWindowRequestHandler.getUUID32;

public class SellFoodRequestHandler extends MessageHandler<SellFoodRequest> {
    @Override
    public boolean handle(SellFoodRequest message) {
        try {
            CP_Table cp_table=new CP_Table();
            List<CP_OV> cp_ovList=cp_table.select();
            List<Food> foodList=message.getFoods();
            double totalPrice=0;
            for (int i = 0; i < foodList.size(); i++) {
                for (int j = 0; j < cp_ovList.size(); j++) {
                    if(foodList.get(i).getID().equals(cp_ovList.get(j).getID())){
                        totalPrice+=cp_ovList.get(j).getPrice();
                    }

                }
            }
            MessageManager.sendMessage(new SellFoodResponse(message.getSession(),totalPrice));
            LS_OV ls_ov=new LS_OV();
            ls_ov.setID(getUUID32());
            ls_ov.setC_ID(message.getID());
            ls_ov.setPrice(totalPrice);
            LS_Table ls_table=new LS_Table();
            ls_table.insert(ls_ov);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
