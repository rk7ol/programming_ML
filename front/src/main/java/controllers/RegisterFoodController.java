package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import send.Dispatcher;

public class RegisterFoodController {
    @FXML
    private static Button buttonRegister;
    @FXML
    private static Button buttonCancel;
    @FXML
    private static TextField textfield1;
    @FXML
    private static TextField textfield2;
    @FXML
    private static TextField textfield3;

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

    @FXML
    //注册菜品按钮
    private void EventRegistercClick()
    {
        System.out.println(textfield1.getText()+textfield2.getText()+textfield3.getText());
        Dispatcher.sendRegisterFoodRequest(new Dispatcher.Callback<Boolean>() {
            @Override
            public void call(Boolean result) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.show();

            }
        }, textfield1.getText(), textfield2.getText(), textfield3.getText());
    }
    @FXML
    private  void eventCancelClick()
    {
        textfield1.setText("");
        textfield2.setText("");
        textfield3.setText("");
    }
}

