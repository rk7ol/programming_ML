package handlers;

import flow.handlers.MessageHandler;
import modules.response.AddFoodsResponse;

//从服务器中选择已有菜品进行添加，返回‘0’为失败，返回‘1’为成功。
public class AddFoodsResponseHandler extends MessageHandler<AddFoodsResponse> {
    @Override
    public boolean handle(AddFoodsResponse message) {
        int flag=message.getFlag();
        return true;
    }
}
