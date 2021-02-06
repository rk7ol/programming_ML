package controllers;

//import controllers.Food;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import send.send;



public class MainPageController<Combobox, string> {



    @FXML
    private Button buttonAdd;

    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Combobox Combobox;


    @FXML
    private TextField textfield1;

    @FXML
    private TextField textfield2;

    @FXML
    private TextField textfield3;

    @FXML
    //确认所选择菜品
    private void yes() {
    }

    @FXML
    private void button1ClickEvent() {
    }

    @FXML
    //菜品名称
    private void addaction1() {
    }

    @FXML
    //销售方式
    private  void addaction2() {
    }

    @FXML
    //价格
    private void addaction3() {
    }

    @FXML
    //添加菜品按钮
    private void eventAddclick()
    {
        addaction1();
        addaction2();
        addaction3();
        System.out.println(textfield1.getText()+textfield2.getText()+textfield3.getText());
        send.sendRegisterFoodRequest("1",textfield1.getText(),textfield2.getText(),textfield3.getText());
    }
}

