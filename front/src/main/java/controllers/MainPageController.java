package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPageController<Combobox> {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Combobox Combobox;


    @FXML
    private void button0ClickEvent(){
    }

    @FXML
    private void button1ClickEvent(){}

    @FXML
    private void buttonAddClickEvent() {
        buttonAdd.setText("clicked");
        System.out.println("Hello");
    }

}
