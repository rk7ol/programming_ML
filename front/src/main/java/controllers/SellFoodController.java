package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

   List<MyChoiceBox> list = new LinkedList<>();
    @FXML
    private void buttonRefeshFoodAction()
    {
        money.setText("");
        if(StartController.ID == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("请登录！");
            alert.show();
        }
        Dispatcher.sendShowWindowFoodsRequest(new Dispatcher.Callback<Foods>() {
            @Override
            public void call(Foods result) {
                foodsPane.getChildren().clear();
                list.clear();
                List<Food> foods = result.getContent();
                //Food food = new Food("dawd", "dwa", 4);
                for(int rank = 0;rank < foods.size();rank++){
                    MyChoiceBox choiceBox = new MyChoiceBox(foods.get(rank));
                    foodsPane.getChildren().add(choiceBox);
                    list.add(choiceBox);
                }
            }
        },StartController.ID);
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
        //验证ID是否为空
        if(StartController.ID == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("请先登录！");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Dispatcher.sendSellFoodRequest(new Dispatcher.Callback<Double>() {
                    @Override
                    public void call(Double result) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                money.setText(String.valueOf(result));
                                status.setText("交易成功！");
                            }
                        });

                    }
                },StartController.ID,foods);

            }
        }).start();
    }

}
