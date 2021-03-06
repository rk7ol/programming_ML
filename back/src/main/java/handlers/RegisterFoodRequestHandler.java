package handlers;

import Database.OP.CP_Table;
import Database.OV.CP_OV;
import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.request.RegisterFoodRequest;
import modules.response.RegisterFoodResponse;

import static handlers.RegisterWindowRequestHandler.getUUID32;

public class RegisterFoodRequestHandler extends MessageHandler<RegisterFoodRequest> {
    @Override
    public boolean handle(RegisterFoodRequest message) {
        boolean flag = false;
        CP_OV cp_ov=new CP_OV();
        String ID=getUUID32();
        cp_ov.setName(message.getName());
        cp_ov.setWay(message.getMethod());
        cp_ov.setPrice(message.getPrice());
        cp_ov.setID(ID);
        try {
            CP_Table cp_table=new CP_Table();
            flag = cp_table.insert(cp_ov);//数据库写入
        } catch (Exception e) {
            e.printStackTrace();
        }
        MessageManager.sendMessage(new RegisterFoodResponse(message.getSession(),flag));
        return true;
    }
}


