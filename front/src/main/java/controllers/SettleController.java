package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import send.Dispatcher;


public class SettleController {
    @FXML
    private TextField textFieldSettle;
    @FXML
    private TextField textFieldFee;
    @FXML
    private TextField textFieldEarn;
    //显示营业额
    @FXML
    private Button buttonSettle;

    @FXML
    private  void EventbuttonSettleClick(){
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
                Dispatcher.sendSettleRequest(new Dispatcher.Callback<Double>() {
                    @Override
                    public void call(Double result) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                textFieldSettle.setText(String.valueOf(result));
                                textFieldFee.setText("100");
                                textFieldEarn.setText(String.valueOf(result - 100));
                            }
                        });
                    }
                },StartController.ID);
            }
        }).start();
    }
}
