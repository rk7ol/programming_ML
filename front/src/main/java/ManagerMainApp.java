import flow.MessageManager;
import handlers.*;
import javafx.application.Application;
import modules.Response;
import modules.response.*;

public class ManagerMainApp {

    public  static void main(String[] args) {

        MessageManager<Response> message = new MessageManager<>();

        message.addHandler(DeleteFoodsResponse.class,new DeleteFoodsResponseHandler());
        //message.addHandler();
        //message.addHandler(RegisterFoodResponse.class, new RegisterFoodResponseHandler());
        //message.addHandler(AddFoodsResponse.class, new AddFoodsResponseHandler());
        //message.addHandler(RegisterWindowResponse.class, new RegisterWindowResponseHandler());
        //message.addHandler(SellFoodResponse.class, new SellFoodResponseHandler());
        //message.addHandler(SettleResponse.class, new SettleResponseHandler());
        //message.addHandler(ShowAllFoodsResponse.class, new ShowAllFoodsResponseHandler());
        //message.addHandler(ShowWindowFoodsResponse.class, new ShowWindowFoodsResponseHandler());

        message.start();

        Application.launch(ManagerViewApp.class);

    }
}
