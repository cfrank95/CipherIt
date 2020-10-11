import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {

  static Scanner keyboard = new Scanner(System.in);



  @FXML
  public TextField messageString;
  public ChoiceBox choiceBox;
  public ChoiceBox offsetChoice;
  public TextField keyString;
  public Label offsetLbl;
  public Label keyPhraseLabel;
  @FXML
  public TextField lblOutput;
  @FXML
  public Button btnOpenFile;

  public void initialize() {
    choiceBoxSelect();

  }

  /**
   * populates choice box on Cryptography tab
   */
  public void choiceBoxSelect() {

    for (CipherType type : CipherType.values()) {
      choiceBox.getItems().add(type);
      choiceBox.getSelectionModel().selectFirst();
    }


  }

  /**
   * populates offset choice box on Cryptography tab
   */
  public void offsetSelect() {
    for (int i = 1; i <= 5; i++) {
      offsetChoice.getItems().add(i);
      offsetChoice.getSelectionModel().selectFirst();
    }
  }


  public void offsetToggle() {
    CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

    if (cipherType == CipherType.Caesar) {

      offsetChoice.setVisible(true);
      offsetSelect();
      offsetLbl.setText("Offset: ");

      keyString.setVisible(false);
      keyPhraseLabel.setText("");


    } else if (cipherType == CipherType.Vigenere) {
      keyPhraseLabel.setText("Key Phrase: ");
      keyString.setVisible(true);

      offsetChoice.setVisible(false);
      offsetLbl.setText("");

    } else {
      offsetChoice.setVisible(false);
      offsetLbl.setText("");

      keyPhraseLabel.setText(" ");
      keyString.setVisible(false);
      keyPhraseLabel.setText("");

    }
  }


  public void encrypt(MouseEvent mouseEvent) {
    String plainString = messageString.getText();
    CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

    switch (cipherType) {
      case Atbash:
        Cipher Atbash = new Atbash();
        lblOutput.setText(Atbash.encrypt(plainString, 0, null).toString());
        break;
      case Caesar:
        int offset = (int) offsetChoice.getValue();
        Cipher Caesar = new Caesar();
        lblOutput.setText(Caesar.encrypt(plainString, offset, null).toString());
        break;
      case Vigenere:
        String key = keyString.getText();
        Cipher Vigenere = new Vigenere();
        lblOutput.setText(Vigenere.encrypt(plainString, 0, key).toString());
        break;
      case Numeric:
        Cipher Numeric = new Numeric();
        lblOutput.setText(Numeric.encrypt(plainString, 0, null).toString());
    }

  }


  public void decrypt(MouseEvent mouseEvent) {
    String messString = messageString.getText();
    CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

    switch (cipherType) {
      case Atbash:
        Cipher Atbash = new Atbash();
        lblOutput.setText(Atbash.decrypt(messString, 0, null).toString());
        break;
      case Caesar:
        int offset = (int) offsetChoice.getValue();
        Cipher Caesar = new Caesar();
        lblOutput.setText(Caesar.decrypt(messString, offset, null).toString());
        break;
      case Vigenere:
        String key = keyString.getText();
        Cipher Vigenere = new Vigenere();
        lblOutput.setText(Vigenere.decrypt(messString, 0, key).toString());
      case Numeric:
        Cipher Numeric = new Numeric();
        lblOutput.setText(Numeric.decrypt(messString, 0, null).toString());
    }
  }


  public void typeSelect(javafx.event.ActionEvent actionEvent) {
    offsetToggle();
  }


  /* ---------------------------------------------
  *  FILE CHOOSER FOR IMAGE CYPHER
  */
  private Desktop desktop = Desktop.getDesktop();

  private void openFile(File file) {
    try {
      desktop.open(file);
    } catch (IOException ex) {
      Logger.getLogger(
              FileChooser.class.getName()).log(
              Level.SEVERE, null, ex
      );
    }
  }

  public void start(final Stage stage){
    stage.setTitle("File Chooser");

    final FileChooser fileChooser = new FileChooser();

    final GridPane inputGridPane = new GridPane();

    GridPane.setConstraints(btnOpenFile, 0, 0);
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