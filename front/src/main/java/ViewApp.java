import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewApp extends Application {

    Scene scene0;//输入卡机，弹出窗口
    Scene scene1;//显示菜品，并且能添加菜品
    Scene scene2;//注册菜品
    Scene scene3;//注册卡机
    Scene scene4;//返回卡机id
    Scene scene5;//营业额结算

    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage=stage;

        FXMLLoader loader0 = new FXMLLoader(Starter.class.getResource("views/Start.fxml"));
        AnchorPane page0 = loader0.load();
        scene0 = new Scene(page0);//开始界面

        FXMLLoader loader1 = new FXMLLoader(Starter.class.getResource("views/MainPage.fxml"));
        AnchorPane page1 = loader1.load();
        scene1 = new Scene(page1);//显示菜品，添加菜品


        FXMLLoader loader2 = new FXMLLoader(Starter.class.getResource("views/register.fxml"));
        AnchorPane page2 = loader2.load();
        scene2 = new Scene(page2);//注册菜品

        FXMLLoader loader3 = new FXMLLoader(Starter.class.getResource("views/RegisterID.fxml"));
        AnchorPane page3 = loader3.load();
        scene3 = new Scene(page3);//注册卡机id

        FXMLLoader loader4 = new FXMLLoader(Starter.class.getResource("views/ID.fxml"));
        AnchorPane page4 = loader4.load();
        scene4 = new Scene(page4);//卡机id

        FXMLLoader loader5 = new FXMLLoader(Starter.class.getResource("views/Account.fxml"));
        AnchorPane page5 = loader5.load();
        scene5 = new Scene(page5);//营业额

        stage.setScene(scene0);

        stage.show();

    }

    private void setScene(Scene scene)
    {
        stage.setScene(scene);
    }
}
