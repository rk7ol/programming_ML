package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import modules.Food;
import modules.Foods;
import send.Dispatcher;
import javafx.scene.layout.AnchorPane;

import java.util.LinkedList;
import java.util.List;

public class FoodManagerController {
    @FXML
    private FlowPane flowpane;

    @FXML
    private Button buttonAsk;

    @FXML
    private Button buttonDelete;

    List<MyChoiceBox> list = new LinkedList<>();

    @FXML
    private void EventbuttonAskClick()
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
                                flowpane.getChildren().clear();
                                list.clear();
                                List<Food> foods = result.getContent();
                                //Food food = new Food("dawd", "dwa", 4);
                                for(int rank = 0;rank < foods.size();rank++){
                                    MyChoiceBox choiceBox = new MyChoiceBox(foods.get(rank));
                                    flowpane.getChildren().add(choiceBox);
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
    private void EventbuttonDeleteClick()
    {
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
                Dispatcher.sendDeleteFoodsRequest(new Dispatcher.Callback<Boolean>() {
                    @Override
                    public void call(Boolean result) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert;
                                if (result) {
                                    alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setContentText("菜品删除成功");

                                } else {
                                    alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setContentText("菜品删除失败");
                                }

                                alert.show();
                            }
                        });
                    }
                }, foods);
            }
        }).start();
    }

}
