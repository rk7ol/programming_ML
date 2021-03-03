package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;



public class TradePageController {
    @FXML
    private Text status;

    @FXML
    private TextField money;
    //显示交易金额

    @FXML
    private Button buttonConfirmTrade;

    @FXML
    private Button buttonConfirmFood;

    @FXML
    private void buttonConfirmTradeAction()
    {
        status.setText("正在交易......");

    }
    private void buttonConfirmFoodAction()
    {
        status.setText("");
    }
}
