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

    public void receive(Scene scene1, Scene scene4, Stage stage){
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
        //将 texfield.getText();获取的内容发送
        //发送完成后，清空输入框，可以继续输入
         texfield.setText("");
    }

    @FXML
    private TextArea showAlltext;

    @FXML
    private Button showAll;

    @FXML
    private void showAllAction()
    {
        send.sendShowAllFoodsRequest();//发送请求
       // 在showAlltext里显示全部菜品
    }


    @FXML
    //该按钮用于在所有菜品都选择完成之后，进行卡机的注册操作
    private void registerAction(){
      //  send.sendRegisterFoodRequest();
        setScene(scene4);
    }

    @FXML
    private void no()
            //取消注册
    {
        setScene(scene1);//返回主界面
    }


    //界面跳转函数
    private void setScene(Scene scene)
    {
        receive(scene1,scene4,stage);
        stage.setScene(scene);
    }


}
