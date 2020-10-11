import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;


public class FileChooser extends Application {
    String name;
    private Desktop desktop = Desktop.getDesktop();

    void setName(String name){
        this.name = name;
    }

    String getName(){
        return name;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("File Chooser");

        final javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();

        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(Controller, 0, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(btnOpenFile);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        stage.setScene(new Scene(rootGroup));
        stage.show();
    }
}
