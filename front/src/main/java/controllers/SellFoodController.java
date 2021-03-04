package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import modules.Food;
import modules.Foods;
import send.Dispatcher;
import controllers.MyChoiceBox;
import java.util.LinkedList;
import java.util.List;


public class SellFoodController {
    @FXML
    private Text status;

    @FXML
    private TextField money;
    //显示交易金额

    @FXML
    private Button buttonConfirmTrade;

    @FXML
    private Button buttonRefreshFood;
    @FXML
    private FlowPane foodsPane;



    /*private static class MyChoiceBox extends CheckBox {
        private Food food;

        public MyChoiceBox(Food food){
            this.food = food;
            setText(food.getName());
        }

        public Food getFood(){
            return food;
        }
    }*/

   List<MyChoiceBox> list;
    @FXML
    private void buttonRefeshFoodAction()
    {
        ///////////////
        String id = "";
        Dispatcher.sendShowWindowFoodsRequest(new Dispatcher.Callback<Foods>() {
            @Override
            public void call(Foods result) {
                foodsPane.getChildren().clear();
                List<Food> foods = result.getContent();
                //Food food = new Food("dawd", "dwa", 4);
                for(int rank = 0;rank < foods.size();rank++){
                    MyChoiceBox choiceBox = new MyChoiceBox(foods.get(rank));
                    foodsPane.getChildren().add(choiceBox);
                    list.add(choiceBox);
                }
            }
        },id);
    }

    @FXML
    private void buttonConfirmTradeAction()
    {
        List<Food> foodlist = new LinkedList<>();
        for(MyChoiceBox box:list){
            if(box.isSelected()){
                foodlist.add(box.getFood());
            }
        }
        Food[] foods = foodlist.toArray(new Food[0]);
        ////////////
        String id = "";
        Dispatcher.sendSellFoodRequest(new Dispatcher.Callback<Double>() {
            @Override
            public void call(Double result) {
                money.setText(String.valueOf(result));
            }
        },id,foods);
        status.setText("正在交易......");

    }

}
