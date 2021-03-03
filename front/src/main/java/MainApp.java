import flow.MessageManager;
import handlers.RegisterFoodResponseHandler;
import javafx.application.Application;
import modules.Message;
import modules.Response;
import modules.response.RegisterFoodResponse;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class MainApp {

    public  static void main(String[] args) {

        MessageManager<Response> message = new MessageManager<>();

        message.addHandler(RegisterFoodResponse.class, new RegisterFoodResponseHandler());

        message.start();

        Application.launch(ViewApp.class);

    }
}
