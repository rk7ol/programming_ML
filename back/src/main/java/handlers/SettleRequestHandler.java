package handlers;

import Database.OP.LS_Table;
import Database.OV.LS_OV;
import flow.MessageManager;
import flow.handlers.MessageHandler;
import modules.request.SettleRequest;
import modules.response.SettleResponse;
import utils.kafka.MessageSender;

import java.util.List;

public class SettleRequestHandler extends MessageHandler<SettleRequest> {
    @Override
    public boolean handle(SettleRequest message) {
        try {
            LS_Table ls_table=new LS_Table();
            List<LS_OV> ls_ovList=ls_table.select();
            double totalLS=0;
            for (int i = 0; i < ls_ovList.size(); i++) {
                if(ls_ovList.get(i).getID().equals(message)){
                    totalLS+=ls_ovList.get(i).getPrice();
                }
            }
            MessageManager.sendMessage(new SettleResponse(totalLS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
