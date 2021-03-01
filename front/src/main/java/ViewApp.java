import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewApp extends Application {

    Scene scene1;
    Scene scene2;
    Scene scene3;
    Scene scene4;

    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage=stage;

        FXMLLoader loader1 = new FXMLLoader(Starter.class.getResource("views/MainPage.fxml"));
        AnchorPane page1 = loader1.load();
        scene1 = new Scene(page1);


        FXMLLoader loader2 = new FXMLLoader(Starter.class.getResource("views/register.fxml"));
        AnchorPane page2 = loader2.load();
        scene2 = new Scene(page2);

        FXMLLoader loader3 = new FXMLLoader(Starter.class.getResource("views/RegisterID.fxml"));
        AnchorPane page3 = loader3.load();
        scene3 = new Scene(page3);

        FXMLLoader loader4 = new FXMLLoader(Starter.class.getResource("views/ID.fxml"));
        AnchorPane page4 = loader4.load();
        scene4 = new Scene(page4);

        stage.setScene(scene1);

        stage.show();

    }

    private void setScene(Scene scene)
    {
        stage.setScene(scene);
    }
}
