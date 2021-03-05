package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartController {
    public static String ID;
    //@FXML
    Scene scene;
    //@FXML
    Scene scene3;
    Scene scene0;
    //@FXML
    Stage stage;
    AnchorPane page0;
    AnchorPane page1;
    AnchorPane page2;
    AnchorPane page4;
    AnchorPane page5;

    StartController startController;

    NavigateController navigateController;
    RegisterWindowController registerWindowController;
    @FXML
    private TextField textfieldID;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonSignin;

    public void receiveController(StartController startController,
                                  NavigateController navigateController,
                                  RegisterWindowController registerWindowController){
        this.navigateController = navigateController;
        this.registerWindowController = registerWindowController;
        this.startController = startController;
    }

    public void receivePage(AnchorPane page0,
                            AnchorPane page1,
                            AnchorPane page2,
                            AnchorPane page4,
                            AnchorPane page5){
        this.page0 = page0;
        this.page1 = page1;
        this.page2 = page2;
        this.page4 = page4;
        this.page5 = page5;
    }

    public void receiveScene(Scene scene,
                             Scene scene0,
                             Scene scene3,
                             Stage stage){
        this.scene=scene;
        this.scene0 = scene0;
        this.scene3=scene3;
        this.stage=stage;
    }

    public void setTextfieldID(String id){
        textfieldID.setText(id);
    }

    /*private void setScene(Scene scene)
    {
        receive(scene1,scene3,stage);
        stage.setScene(scene);
    }*/

    public void setSceneToNavigate()
    {
        navigateController.setMainStage(stage);
        stage.setScene(scene);
        //navigateController.addPage("卡机id登录", page0);
        navigateController.addPage("选择添加菜品", page1);
        navigateController.addPage("注册新菜品", page2);
        //controller.addPage("注册新卡机", page3);
        navigateController.addPage("SellFood", page4);
        navigateController.addPage("营业额结算", page5);

    }

    @FXML
    private void eventButtonLoginClick(){
        //发送卡机id函数
        ID = textfieldID.getText();
        setSceneToNavigate();
        //controller.addPage("卡机id登录", page);

        //将卡机ID发送，发送后按照id显示菜品
       // setScene(scene1);
    }

    @FXML
    private void eventButtonCancelClick(){
        textfieldID.setText("");
    }//清空输入框


    @FXML
    private void eventButtonSigninClick()
    {
        registerWindowController.receiveController(startController);
        registerWindowController.receiveScene(scene0,stage);
        stage.setScene(scene3);

        //receive(scene1,scene3,stage);
        //setScene(scene3);
    }
}
