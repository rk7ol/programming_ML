package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class NavigateController {

    @FXML
    private VBox pageBox;

    @FXML
    private AnchorPane displayPane;

    @FXML
    private AnchorPane mainPane;


    private Stage mainStage;

    private List<Pane> panes = new ArrayList<>();


    public void addPage(String name, Pane pane) {
        Button navigateButton = createNavigateButton(name, pane);

        pageBox.getChildren().add(navigateButton);
    }

    @FXML
    private void initialize() {

    }


    private Button createNavigateButton(String name, Pane pane) {
        Button button = new Button(name);

        button.setPrefWidth(80);
        button.setPrefHeight(20);




        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                showPane(pane);

                mainStage.setWidth(pane.getPrefWidth() + pageBox.getWidth());
                mainStage.setHeight(Double.max(pane.getPrefWidth(), pageBox.getPrefWidth()));

                //stateResize(100, pane.getPrefWidth() + pageBox.getWidth(), Double.max(pane.getPrefWidth(), pageBox.getPrefWidth()));


                if (!panes.contains(pane)) {
                    //preload
                    panes.add(pane);
                    handle(actionEvent);
                }
            }
        });

        return button;
    }

    private void showPane(Pane pane) {
        if (!displayPane.getChildren().isEmpty())
            displayPane.getChildren().remove(0);

        displayPane.getChildren().add(pane);
    }

    private void stateResize(long intervalTime, double width, double height) {

        long times = 10;

        long timeStep = intervalTime / times;

        double deltaWidth = mainStage.getWidth() - width;

        double deltaHeight = mainStage.getHeight() - height;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        double step = deltaHeight / times;

                        for (int i = 0; i < times; i++) {
                            try {
                                Thread.sleep(timeStep);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            mainStage.setHeight(mainStage.getHeight() - step);


                        }

                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        double step = deltaWidth / times;

                        for (int i = 0; i < times; i++) {
                            try {
                                Thread.sleep(timeStep);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            mainStage.setWidth(mainStage.getWidth() - step);

                        }

                    }
                }).start();

            }
        });


    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }


}
