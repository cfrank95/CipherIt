import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  /**
   * Main method for program.
   *
   * @param args -- Command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Initializes JavaFX GUI window.
   *
   * @param primaryStage - Primary stage for application
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setTitle("Encryption Project");

    primaryStage.setScene(scene);

    primaryStage.show();

  }
}
