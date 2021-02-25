package handlers;

import Database.OP.KC_Table;
import Database.OV.KC_OV;
import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.Food;
import modules.request.RegisterWindowRequest;
import modules.response.AddFoodsResponse;
import modules.response.RegisterWindowResponse;

import java.util.List;
import java.util.UUID;

public class RegisterWindowRequestHandler extends MessageHandler<RegisterWindowRequest> {

    public static String getUUID32(){

        return UUID.randomUUID().toString().replace("-", "").toLowerCase();

    }

    @Override
    public boolean handle(RegisterWindowRequest message) {
        String ID=getUUID32();
        List<Food> foodList = message.getFoods();
        try {
            KC_Table kc_table=new KC_Table();
            for (int i = 0; i < foodList.size(); i++) {
                KC_OV kc_ov = new KC_OV(foodList.get(i).getID(), ID);
            }
            MessageManager.sendMessage(new RegisterWindowResponse(ID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
