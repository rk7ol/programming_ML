package handlers;

import Database.DB;
import Database.OPFactory;
import Database.OV.CP_OV;
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
            List<Food> foodList= message.getFoods();
            for (int i = 0; i < foodList.size(); i++) {
                CP_OV cp_ov=new CP_OV(foodList.get(i).getID(),foodList.get(i).getName(),foodList.get(i).getMethod(),foodList.get(i).getPrice());
                flag=cp_table.delete(cp_ov);
            }
            MessageManager.sendMessage(new DeleteFoodsResponse(message.getSession(),flag));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
