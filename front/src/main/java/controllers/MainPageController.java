package controllers;

//import controllers.Food;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modules.Food;
import send.send;



public class MainPageController{

    Scene scene1;
    Scene scene2;
    Scene scene3;
    Scene scene4;
    Scene scene5;

    Stage stage;

    public void receive(Scene scene1, Scene scene2, Scene scene3, Scene scene4,Scene scene5, Stage stage){
        this.scene1=scene1;
        this.scene2=scene2;
        this.scene3=scene3;
        this.scene4=scene4;
        this.scene5=scene5;
        this.stage=stage;
    }

    private void setScene(Scene scene)
    {
        receive(scene1,scene2,scene3,scene4,scene5,stage);
        stage.setScene(scene);
    }


    @FXML
    private Button buttonAdd;

    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private ComboBox<Food> Combobox;

    @FXML
    private TextField textfield1;

    @FXML
    private TextField textfield2;

    @FXML
    private TextField textfield3;

    @FXML
    //确认所选择菜品
    private void yes()
    {
        //将已选择的菜品信息进行发送
        //弹出交易页面scene6
    }

    @FXML
    private void button1ClickEvent() {
        textfield3.setText("");
        textfield2.setText("");
        textfield1.setText("");
        //清空输入框
    }

    @FXML
    //添加菜品按钮
    private void eventAddclick()
    {
        System.out.println(textfield1.getText()+textfield2.getText()+textfield3.getText());
        send.sendRegisterFoodRequest(textfield1.getText(),textfield2.getText(),textfield3.getText());
    }

    @FXML
    private Button buttonScene2;

    @FXML
    //注册菜品
    private void scene2action(){
        setScene(scene2);
    }

    @FXML
    private Button buttonScene3;

    @FXML
    //注册卡机
    private void scene3action(){
        setScene(scene3);
    }

    @FXML
    private Button buttonScene5;

    @FXML
    //结算营业额
    private void scene5action(){
        setScene(scene5);
    }

}

