package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import send.Dispatcher;

public class RegisterController {
    @FXML
    private Button buttonRegister;

    @FXML
    private TextField textfield1;

    @FXML
    private TextField textfield2;

    @FXML
    private TextField textfield3;

    @FXML
    //菜品名称
    private void register1() {
    }

    @FXML
    //销售方式
    private  void register2() {
    }

    @FXML
    //价格
    private void register3() {
    }

    @FXML
    //注册菜品按钮
    private void eventRegisterclick()
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
}

