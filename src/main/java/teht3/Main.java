package teht3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Form loginForm = new Form();
        primaryStage.setScene(new Scene(loginForm, 300, 200));
        primaryStage.show();
    }
}
