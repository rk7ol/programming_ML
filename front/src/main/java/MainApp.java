import flow.MessageManager;
import handlers.*;
import javafx.application.Application;
import modules.Message;
import modules.Response;
import modules.response.*;

import java.util.UUID;

public class MainApp {

    public  static void main(String[] args) {

        MessageManager<Response> message = new MessageManager<>();

        message.addHandler(RegisterFoodResponse.class, new RegisterFoodResponseHandler());
        message.addHandler(AddFoodsResponse.class, new AddFoodsResponseHandler());
        message.addHandler(RegisterWindowResponse.class, new RegisterWindowResponseHandler());
        message.addHandler(SellFoodResponse.class, new SellFoodResponseHandler());
        message.addHandler(SettleResponse.class, new SettleResponseHandler());
        message.addHandler(ShowAllFoodsResponse.class, new ShowAllFoodsResponseHandler());
        message.addHandler(ShowWindowFoodsResponse.class, new ShowWindowFoodsResponseHandler());

        message.start();

        Application.launch(ViewApp.class);

        message.stop();

    }
}
