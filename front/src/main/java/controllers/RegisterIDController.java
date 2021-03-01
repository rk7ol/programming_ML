package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import send.send;


public class RegisterIDController {
    Scene scene4;
    Scene scene1;
    Stage stage;

    public void receive(Scene scene1, Scene scene2, Scene scene3, Scene scene4, Stage stage){
        this.scene1=scene1;
        this.scene4=scene4;
        this.stage=stage;
    }

    @FXML
    private TextField texfield;

    @FXML
    private Button add;

    @FXML
    //选择菜品后的确认按钮
    private void addAction(){
         texfield.getText();
         texfield.setText(" ");
    }

    @FXML
    private TextArea showAlltext;

    @FXML
    private Button showAll;

    @FXML
    private void showAllAction()
    {
        send.sendShowAllFoodsRequest();//发送请求
       // showAlltext显示全部菜品
    }


    @FXML
    private void registerAction(){
      //  send.sendRegisterFoodRequest();
        setScene(scene4);
    }

    @FXML
    private void no(){
        setScene(scene1);//返回主界面
    }


    private void setScene(Scene scene)
    {
        stage.setScene(scene);
    }


}
