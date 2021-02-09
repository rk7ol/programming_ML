package send;
import flow.MessageManager;
import modules.Food;
import modules.request.*;


public class send {
    //从服务器中选择已有菜品进行添加
    public static void sendAddFoodsRequest(Food... foods){
        MessageManager.sendMessage(new AddFoodsRequest(foods));
    }
    //注册菜品到服务器
    public static void sendRegisterFoodRequest(String name, String method, String price){
        MessageManager.sendMessage(new RegisterFoodRequest(name,method,Double.parseDouble(price)));
    }
    //注册卡机
    public static void sendRegisterWindowRequest(Food... foods){
        MessageManager.sendMessage(new RegisterWindowRequest(foods));
    }
    //菜品交易，服务器进行结算
    public static void sendSellFoodRequest(Food... foods){
        MessageManager.sendMessage(new SellFoodRequest(foods));
    }
    //营业额结算
    public static void sendRegisterSettleRequest(String ID){
        MessageManager.sendMessage(new SettleRequest(ID));
    }
    //显示所有菜品
    public static void sendShowAllFoodsRequest(){
        MessageManager.sendMessage(new ShowAllFoodsRequest());
    }
    //显示卡机菜品
    public static void sendShowAllWindowFoodsRequest(String ID){
        MessageManager.sendMessage(new ShowWindowFoodsRequest(ID));
    }
}
