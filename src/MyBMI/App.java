package MyBMI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BMI.fxml"));
        primaryStage.setTitle("MyBMI");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
