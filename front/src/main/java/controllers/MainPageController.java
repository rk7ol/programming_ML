package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPageController {

    @FXML
    private Button buttonExample;

    @FXML
    private void buttonExampleClickEvent(){
        buttonExample.setText("clicked");
        System.out.println("Hello");
    }

}
