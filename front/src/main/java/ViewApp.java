import controllers.NavigateController;
import controllers.RegisterFoodController;
import controllers.RegisterWindowController;
import controllers.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewApp extends Application {

    Scene scene0;//输入卡机，弹出窗口 //Start.fxml
    Scene scene1;//显示菜品，并且能添加菜品 //AddFoods.fxml
    Scene scene2;//注册菜品 //RegisterFood.fxml
    Scene scene3;//注册卡机 //RegisterWindow.fxml
    Scene scene5;//营业额结算 //Settle.fxml
    Scene scene6;//交易页面  //SellFood.fxml


    @Override
    public void start(Stage stage) throws Exception {

        stage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(Starter.class.getResource("views/Navigate.fxml"));
        AnchorPane navigate = loader.load();
        Scene scene = new Scene(navigate);

        NavigateController navigateController = loader.getController();

        /*navigateController.setMainStage(stage);
        stage.setScene(scene);*/


        FXMLLoader loader0 = new FXMLLoader(Starter.class.getResource("views/Start.fxml"));
        AnchorPane page0 = loader0.load();
        scene0 = new Scene(page0);//开始界面
        StartController startController = loader0.getController();

        FXMLLoader loader1 = new FXMLLoader(Starter.class.getResource("views/AddFoods.fxml"));
        AnchorPane page1 = loader1.load();
        scene1 = new Scene(page1);//显示菜品，添加菜品


        FXMLLoader loader2 = new FXMLLoader(Starter.class.getResource("views/RegisterFood.fxml"));
        AnchorPane page2 = loader2.load();
        scene2 = new Scene(page2);//注册菜品
        RegisterFoodController registerFoodController = loader2.getController();

        FXMLLoader loader3 = new FXMLLoader(Starter.class.getResource("views/RegisterWindow.fxml"));
        AnchorPane page3 = loader3.load();
        scene3 = new Scene(page3);//注册卡机id
        RegisterWindowController registerWindowController = loader3.getController();

        FXMLLoader loader4 = new FXMLLoader(Starter.class.getResource("views/SellFood.fxml"));
        AnchorPane page4 = loader4.load();
        scene6 = new Scene(page4);//交易页面

        FXMLLoader loader5 = new FXMLLoader(Starter.class.getResource("views/Settle.fxml"));
        AnchorPane page5 = loader5.load();
        scene5 = new Scene(page5);//营业额


        startController.receiveScene(scene,scene0,scene3,stage);
        startController.receivePage(page0,page1,page2,page4,page5);
        startController.receiveController(startController,navigateController,registerWindowController);

        /*navigateController.addPage("卡机id登录", page0);
        navigateController.addPage("选择添加菜品", page1);
        navigateController.addPage("注册新菜品", page2);
        //controller.addPage("注册新卡机", page3);
        navigateController.addPage("SellFood", page4);
        navigateController.addPage("营业额结算", page5);
        //controller.addPage("卡机id登录", page);*/

        stage.setScene(scene0);
        stage.show();

    }


}
