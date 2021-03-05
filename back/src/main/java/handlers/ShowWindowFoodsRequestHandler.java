package handlers;

import Database.OP.CP_Table;
import Database.OP.KC_Table;
import Database.OV.CP_OV;
import Database.OV.KC_OV;
import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.Food;
import modules.request.ShowWindowFoodsRequest;
import modules.response.ShowWindowFoodsResponse;

import java.util.LinkedList;
import java.util.List;

public class ShowWindowFoodsRequestHandler extends MessageHandler<ShowWindowFoodsRequest> {
    @Override
    public boolean handle(ShowWindowFoodsRequest message) {
        List<KC_OV> kc_ovList;
        List<Food> foodList=new LinkedList<>();
        try {
            String ID=message.getID();
            KC_Table kc_table=new KC_Table();
            kc_ovList=kc_table.select();
            CP_Table cp_table=new CP_Table();
            List<CP_OV> cp_ovList=cp_table.select();
            for (int i = 0; i < kc_ovList.size(); i++) {
                KC_OV kc_ov=kc_ovList.get(i);
                if(kc_ov.getK_ID().equals(ID)){
                    for (int j = 0; j < cp_ovList.size(); j++) {
                        CP_OV cp_ov=cp_ovList.get(j);
                        if(cp_ov.getID().equals(kc_ov.getC_ID())){
                            Food food=new Food(cp_ov.getID(),cp_ov.getName(),cp_ov.getPrice(),cp_ov.getWay());
                            foodList.add(food);
                        }
                    }
                }
            }
            Food[] foods=new Food[foodList.size()];
            for (int i = 0; i < foodList.size(); i++) {
                foods[i]=foodList.get(i);
            }
            MessageManager.sendMessage(new ShowWindowFoodsResponse(message.getSession(),foods));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
