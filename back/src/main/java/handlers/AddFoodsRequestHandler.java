package handlers;

import Database.OP.KC_Table;
import Database.OV.KC_OV;
import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.Food;
import modules.request.AddFoodsRequest;
import modules.response.AddFoodsResponse;
import modules.response.RegisterFoodResponse;

import java.util.List;

public class AddFoodsRequestHandler extends MessageHandler<AddFoodsRequest> {
    @Override
    public boolean handle(AddFoodsRequest message) {
        boolean flag = true;
        List<Food> foodList = message.getFoods();
        try {
            KC_Table kc_table = new KC_Table();
            for (int i = 0; i < foodList.size(); i++) {
                KC_OV kc_ov = new KC_OV(message.getID(), foodList.get(i).getID());
                flag = kc_table.insert(kc_ov);
                if (flag == false) break;
            }
            MessageManager.sendMessage(new AddFoodsResponse(message.getSession(),flag));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
