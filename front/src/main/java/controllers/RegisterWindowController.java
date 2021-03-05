package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import modules.Food;
import modules.Foods;
import send.Dispatcher;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;


public class RegisterWindowController {

    @FXML
    private FlowPane foodsPane;

    @FXML
    private Button buttonShowAllFood;

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField WindowID;

    @FXML
    private Button buttonConfirmRegist;
    List<MyChoiceBox> list = new LinkedList<>();
    private StartController startController;
    private Scene scene0;
    private Stage stage;

    public void receiveController(StartController startController){
        this.startController = startController;
    }

    public void receiveScene(Scene scene0,Stage stage){
        this.scene0 = scene0;
        this.stage = stage;
    }


    /*private static class MyChoiceBox extends CheckBox{
        private Food food;

        public MyChoiceBox(Food food){
            this.food = food;
            setText(food.getName());
        }

        public Food getFood(){
            return food;
        }
    }*/

    private void setWindowID(String id){
        WindowID.setText(id);
    }
    @FXML
    public void eventShowAllFoodClick()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Dispatcher.sendShowAllFoodsRequest(new Dispatcher.Callback<Foods>() {
                    @Override
                    public void call(Foods result) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                foodsPane.getChildren().clear();
                                list.clear();
                                List<Food> foods = result.getContent();
                                //Food food = new Food("dawd", "dwa", 4);
                                for(int rank = 0;rank < foods.size();rank++){
                                    MyChoiceBox choiceBox = new MyChoiceBox(foods.get(rank));
                                    foodsPane.getChildren().add(choiceBox);
                                    list.add(choiceBox);
                                }
                            }
                        });

                    }
                });
            }
        }).start();
    }

    @FXML
    //该按钮用于在所有菜品都选择完成之后，进行卡机的注册操作
    private void eventButtonConfirmRegistClick(){
        List<Food> foodlist = new LinkedList<>();
        for(MyChoiceBox box:list){
            if(box.isSelected()){
                foodlist.add(box.getFood());
            }
        }
        Food[] foods = foodlist.toArray(new Food[0]);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Dispatcher.sendRegisterWindowRequest(new Dispatcher.Callback<String>() {
                    @Override
                    public void call(String result) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                setWindowID(result);
                                startController.setTextfieldID(result);
                                stage.setScene(scene0);
                            }
                        });
                    }
                }, foods);
            }
        }).start();
    }

    @FXML
    private void eventButtonCancelClick()
    {
        stage.setScene(scene0);
    }
}
