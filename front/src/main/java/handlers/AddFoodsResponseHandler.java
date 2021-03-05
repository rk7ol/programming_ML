package handlers;

import flow.handlers.MessageHandler;
import modules.response.AddFoodsResponse;
import send.Dispatcher;

//从服务器中选择已有菜品进行添加。
public class AddFoodsResponseHandler extends MessageHandler<AddFoodsResponse> {
    @Override
    public boolean handle(AddFoodsResponse message) {
        boolean flag=message.getFlag();
        Dispatcher.saveResponse(message.getSession(), flag);
        return true;
    }
}
