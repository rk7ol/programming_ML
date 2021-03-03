package controllers;

import modules.Food;
import java.util.List;
import controllers.*;

public class OutputHandler {
    //调用该函数，可以在注册卡机界面(RegisterFood.fxml)输出所有菜品
    public void ShowAllFoodInPageRegister(List<Food> foods) {
        //RegisterIDController.ShowAllFoodInTable(foods);
    }
    //调用该函数，可以在销售页面(TradePage.fxml)输出卡机菜品
    public void ShowWindowFoodInPageTrade(List<Food> foods){

    }
    //调用该函数，可以在销售页面(TradePage.fxml)输出总金额
    public void ShowTradeInPageTrade(){

    }

    //调用该函数，可以在结算界面(Account.fxml)输出结算金额
    public void ShowAccountInPageAccount(){

    }
    //
}
