package handlers;

import Database.OP.KC_Table;
import Database.OP.KJ_Table;
import Database.OV.KC_OV;
import Database.OV.KJ_OV;
import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.Food;
import modules.request.RegisterWindowRequest;
import modules.response.AddFoodsResponse;
import modules.response.RegisterWindowResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
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
            MessageManager.sendMessage(new RegisterWindowResponse("",ID));
            KJ_Table kj_table=new KJ_Table();
            KJ_OV kj_ov=new KJ_OV();
            kj_ov.setID(ID);
            kj_ov.setNum(foodList.size());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String Time=df.format(new Date()).toString();
            kj_ov.setTime(Time);
            kj_table.insert(kj_ov);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
