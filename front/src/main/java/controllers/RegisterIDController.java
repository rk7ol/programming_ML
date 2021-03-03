package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modules.Food;
import java.util.List;


public class RegisterIDController {
    Scene scene4;
    Scene scene1;
    Stage stage;

    public void receive(Scene scene1, Scene scene4, Stage stage){
        this.scene1=scene1;
        this.scene4=scene4;
        this.stage=stage;
    }

    @FXML
    private static TextField textfield;

    @FXML
    private static Button add;
    //选择菜品后的确认按钮

    @FXML
    private void addAction(){
        add.setText("ok");
        //将 texfield.getText();获取的内容发送
        //发送完成后，清空输入框，可以继续输入
        //texfield.setText("hello");
    }

    @FXML
    private TextArea showAlltext;

    @FXML
    private Button showAll;

    @FXML
    private CheckBox hello;

    @FXML
    private void showAllAction()
    {
        textfield.setText("");
        hello.setText("hello");
        //texfield.setText("hello!");
        //send.sendShowAllFoodsRequest();//发送请求
        //cb = new CheckBox("first");
        //hello.setText("hello");
        //cb.setLayoutX(100);
        //cb.setLayoutY(100);
    }
    // 在showAlltext里显示全部菜品
    @FXML
    public void ShowAllFoodInTable(List<Food> foods)
    {
        for(int rank = 0;rank < foods.size();rank++){
            //CheckBox
        }
    }

    @FXML
    //该按钮用于在所有菜品都选择完成之后，进行卡机的注册操作
    private void registerAction(){
      //  send.sendRegisterFoodRequest();
    }

    @FXML
    private void no()
            //取消注册
    {
        textfield.setText("");

    }


    //界面跳转函数
    private void setScene(Scene scene)
    {
        receive(scene1,scene4,stage);
        stage.setScene(scene);
    }
}
