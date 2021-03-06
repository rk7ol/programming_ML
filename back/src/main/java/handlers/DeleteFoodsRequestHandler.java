package handlers;

import Database.DB;
import Database.OPFactory;
import Database.OV.CP_OV;
import Database.OV.KC_OV;
import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.Food;
import modules.request.DeleteFoodsRequest;
import modules.response.DeleteFoodsResponse;

import java.util.List;

public class DeleteFoodsRequestHandler extends MessageHandler<DeleteFoodsRequest> {
    @Override
    public boolean handle(DeleteFoodsRequest message){
        OPFactory opFactory=new OPFactory();
        Boolean flag=false;
        try {
            DB cp_table=opFactory.createTable("CP_Table");
            DB kc_table=opFactory.createTable("KC_Table");
            List<Food> foodList= message.getFoods();
            for (int i = 0; i < foodList.size(); i++) {
                KC_OV kc_ov=new KC_OV();
                kc_ov.setC_ID(foodList.get(i).getID());
                CP_OV cp_ov=new CP_OV(foodList.get(i).getID(),foodList.get(i).getName(),foodList.get(i).getMethod(),foodList.get(i).getPrice());
                kc_table.delete(kc_ov);
                flag=cp_table.delete(cp_ov);
            }
            MessageManager.sendMessage(new DeleteFoodsResponse(message.getSession(),flag));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
