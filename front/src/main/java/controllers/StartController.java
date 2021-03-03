package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartController {
    Scene scene1;
    Scene scene3;
    Stage stage;

    public void receive(Scene scene1,Scene scene3,Stage stage){
        this.scene1=scene1;
        this.scene3=scene3;
        this.stage=stage;
    }

    private void setScene(Scene scene)
    {
        receive(scene1,scene3,stage);
        stage.setScene(scene);
    }

    @FXML
    private TextField textfield0;

    @FXML
    private Button yesbutton;

    @FXML
    private Button nobutton;

    @FXML
    private void yesaction(){
        //发送卡机id函数
        textfield0.getText();
        //将卡机ID发送，发送后按照id显示菜品
       // setScene(scene1);
    }

    @FXML
    private void noaction(){
        textfield0.setText("");
    }//清空输入框

    @FXML
    private Button scene3button;

    @FXML
    private void scene3action()
    {
        receive(scene1,scene3,stage);
      //  setScene(scene3);
    }
}
