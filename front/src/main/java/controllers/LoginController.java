package controllers;

import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modules.Food;
import modules.Foods;
import send.Dispatcher;
import javafx.scene.layout.AnchorPane;

public class LoginController {
    Scene scene;
    Stage stage;
    AnchorPane page1;
    AnchorPane page2;
    NavigateController navigateController;
    @FXML
    private TextField textfieldID;

    @FXML
    private PasswordField textfieldpassword;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonCancel;

    public void receiveScene(Scene scene,Stage stage){
        this.scene = scene;
        this.stage = stage;
    }
    public void receivePage(AnchorPane page1,AnchorPane page2){
        this.page1 = page1;
        this.page2 = page2;
    }
    public void receiveController(NavigateController navigateController){
        this.navigateController = navigateController;
    }
    @FXML
    private void EventButtonLoginClick(){
        String ID = textfieldID.getText();
        String password = textfieldpassword.getText();

        if(ID.equals("administrator")&&password.equals("888888")){

            navigateController.setMainStage(stage);
            stage.setScene(scene);
            //navigateController.addPage("卡机id登录", page0);
            navigateController.addPage("食品删除", page1);
            navigateController.addPage("注册菜品", page2);

            stage.show();
        }
        else{
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("账号密码错误！");
            alert.show();
            textfieldpassword.setText("");
            textfieldID.setText("");
        }
        //stage.setScene(scene);
        //textfieldpassword.setText("");
        //textfieldID.setText("");
    }

    @FXML
    private void EventButtonCancelClick(){
        textfieldID.setText("");
        textfieldpassword.setText("");
    }
}
