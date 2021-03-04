package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import send.Dispatcher;


public class SettleController {
    @FXML
    private TextField result;
    //显示营业额
    @FXML
    private Button buttonSettle;

    @FXML
    private  void EventbuttonSettleClick(){
        //////////////////
        String id = "";
        Dispatcher.sendSettleRequest(new Dispatcher.Callback<Double>() {
            @Override
            public void call(Double result) {

            }
        },id);
    }
}
