import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Starter.class.getResource("views/MainPage.fxml"));

        AnchorPane anchorPane = loader.load();

        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);

        stage.show();

    }
}
