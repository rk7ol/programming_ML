package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class TradePage {
    @FXML
    private Text text1;

    @FXML
    private Button yesbutton;

    @FXML
    private void yes()
    {
        text1.setText("交易成功！");
    }

}
