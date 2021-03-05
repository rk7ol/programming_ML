package controllers;

//import controllers.Food;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modules.Food;
import modules.Foods;
import send.Dispatcher;
import java.util.List;

public class AddFoodsController {

    @FXML
    private Button buttonConfirmAdd;
    @FXML
    private Button buttonShowFood;
    @FXML
    private ComboBox<Food> combobox;
    @FXML
    private TextField textfieldfoodname;
    @FXML
    private TextField textfieldmethod;
    @FXML
    private TextField textfieldprice;

    Food currentfood = null;

    private void setComboBox(List<Food> foods){
        //foodlist = foods;
        for(int rank = 0;rank < foods.size();rank++) {
            combobox.getItems().add(foods.get(rank));
        }
    }


    @FXML
    //确认所选择菜品
    private void eventButtonShowFoodClick()
    {
        //将已选择的菜品信息进行发送
        new Thread(new Runnable() {
            @Override
            public void run() {
                Dispatcher.sendShowAllFoodsRequest(new Dispatcher.Callback<Foods>() {
                    @Override
                    public void call(Foods result) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //foodsPane.getChildren().clear();
                                List<Food> foods = result.getContent();
                                setComboBox(foods);
                            }
                        });
                    }
                });
            }
        }).start();

        //弹出交易页面scene6
    }
    @FXML
    private void eventComboBoxClick()
    {
        currentfood = combobox.getValue();
        textfieldfoodname.setText(currentfood.getName());
        textfieldmethod.setText(currentfood.getMethod());
        textfieldprice.setText(String.valueOf(currentfood.getPrice()));
    }
    @FXML
    //添加菜品按钮
    private void eventbuttonConfirmAddclick()
    {
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
                Dispatcher.sendAddFoodsRequest(new Dispatcher.Callback<Boolean>() {
                    @Override
                    public void call(Boolean result) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert;
                                if (result){
                                    alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setContentText("菜品添加成功");

                                }else {
                                    alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setContentText("菜品添加失败");
                                }
                                alert.show();
                            }
                        });
                    }
                },StartController.ID,currentfood);
            }
        }).start();
    }
}

