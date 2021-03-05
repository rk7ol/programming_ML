package handlers;

import flow.handlers.MessageHandler;
import modules.response.DeleteFoodsResponse;
import send.Dispatcher;
//从服务器中选择已有菜品进行删除。
public class DeleteFoodsResponseHandler extends MessageHandler<DeleteFoodsResponse>{
    @Override
    public boolean handle(DeleteFoodsResponse message) {
        boolean flag=message.getFlag();
        Dispatcher.saveResponse(message.getSession(), flag);
        return true;
    }
}
