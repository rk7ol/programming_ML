package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import modules.Food;
import modules.Foods;
import send.Dispatcher;

import java.util.List;

public class RegisterFoodController {
    @FXML
    private Button buttonRegister;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextField textfield1;
    @FXML
    private TextField textfield2;
    @FXML
    private TextField textfield3;

    /*//菜品名称
    @FXML
    private void register1() {
    }

    @FXML
    //销售方式
    private  void register2() {
    }

    @FXML
    //价格
    private void register3() {
    }*/

    private void setText(){
        textfield3.setText("dwa");
    }
    @FXML
    //注册菜品按钮
    private void EventRegistercClick()
    {
        System.out.println(textfield1.getText()+textfield2.getText()+textfield3.getText());

        Dispatcher.sendShowAllFoodsRequest(new Dispatcher.Callback<Foods>() {
            @Override
            public void call(Foods result) {
                List<Food> foods = result.getContent();


            }
        });


        Dispatcher.sendRegisterFoodRequest(new Dispatcher.Callback<Boolean>() {
            @Override
            public void call(Boolean result) {

                Alert alert;
                if (result){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("菜品注册成功");

                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("菜品注册失败");
                }

                alert.show();

            }
        }, textfield1.getText(), textfield2.getText(), textfield3.getText());
    }
    @FXML
    private void eventCancelClick()
    {
        textfield1.setText("");
        textfield2.setText("");
        textfield3.setText("");
    }
}

