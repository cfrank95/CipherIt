/*---------------------------------------------------------
 file: Controller.java
   by: Adam Paul, Jaden Williams,
       Christopher Frank, Joseph Morelli
  for: Controller class for all GUI interactions
 ---------------------------------------------------------*/

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller class for user interactions with the GUI
 */
public class Controller {

  @FXML
  public TextField messageString;
  public ChoiceBox choiceBox;
  public ChoiceBox offsetChoice;
  public TextField keyString;
  public Label offsetLbl;
  public Label keyPhraseLabel;
  public ImageView imageDisplay;
  public AnchorPane anchorPane;
  public TextArea textLog;
  @FXML
  private TextField lblOutput;
  ArrayList<String> logs = new ArrayList<>() ;


  public void initialize() {
    choiceBoxSelect();

  }

  /**
   * Populates Cipher Type choice box
   */
  public void choiceBoxSelect() {

    for (CipherType type : CipherType.values()) {
      choiceBox.getItems().add(type);
      choiceBox.getSelectionModel().selectFirst();
    }
  }

  /**
   * Populates Offset choice box
   */
  public void offsetSelect() {
    for (int i = 1; i <= 5; i++) {
      offsetChoice.getItems().add(i);
    }
    offsetChoice.getSelectionModel().selectFirst();
  }

  /**
   * Toggles off irrelevant UI elements based off of the selected CipherType
   */
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

  /**
   * Determines what type of encryption will be used based off of the CipherType selected by the
   * Cipher Type ChoiceBox.
   *
   * @param mouseEvent - occurs when mouse clicks the encrypt button
   */
  public void encrypt(MouseEvent mouseEvent) {
    String plainString = messageString.getText();
    CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

    textLog.setText(" ");
    CipherLog recordLog = new CipherLog(plainString, cipherType.toString());
    logs.add(recordLog.toString());
    for(String a : logs){
      textLog.appendText(a + "\n");
    }

    switch (cipherType) {
      case Atbash:
        Ciphers Atbash = new Atbash();
        lblOutput.setText(Atbash.encrypt(plainString, 0, null).toString());
        break;
      case Caesar:
        int offset = (int) offsetChoice.getValue();
        Ciphers Caesar = new Caesar();
        lblOutput.setText(Caesar.encrypt(plainString, offset, null).toString());
        break;
      case Vigenere:
        String key = keyString.getText();
        Ciphers Vigenere = new Vigenere();
        lblOutput.setText(Vigenere.encrypt(plainString, 0, key).toString());
        break;
      case Numeric:
        Ciphers Numeric = new Numeric();
        lblOutput.setText(Numeric.encrypt(plainString, 0, null).toString());
    }
  }

  /**
   * Determines what type of decryption will be used based off of the CipherType selected by the
   * Cipher Type ChoiceBox.
   *
   * @param mouseEvent - occurs when mouse clicks the decrypt button
   */
  public void decrypt(MouseEvent mouseEvent) {
    String messString = messageString.getText();
    CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

    textLog.setText(" ");
    CipherLog recordLog = new CipherLog(messString, cipherType.toString());
    logs.add(recordLog.toString());
    for(String a : logs){
      textLog.appendText(a + "\n");
    }

    switch (cipherType) {
      case Atbash:
        Ciphers Atbash = new Atbash();
        lblOutput.setText(Atbash.decrypt(messString, 0, null).toString());
        break;
      case Caesar:
        int offset = (int) offsetChoice.getValue();
        Ciphers Caesar = new Caesar();
        lblOutput.setText(Caesar.decrypt(messString, offset, null).toString());
        break;
      case Vigenere:
        String key = keyString.getText();
        Ciphers Vigenere = new Vigenere();
        lblOutput.setText(Vigenere.decrypt(messString, 0, key).toString());
        break;
      case Numeric:
        Ciphers Numeric = new Numeric();
        lblOutput.setText(Numeric.decrypt(messString, 0, null).toString());
    }
  }

  /**
   * Calls offsetToggle function when a cipher type is selected
   *
   * @param actionEvent - occurs when mouse clicks on a cipher type
   */
  public void typeSelect(ActionEvent actionEvent) {
    offsetToggle();
  }

  /**
   * Links to Adam's Linkedin when clicked.
   *
   * @param event - Opens link when it's clicked on
   */
  @FXML
  void adamLinkedIn(ActionEvent event) {
    try {
      URI uri = new URI("https://www.linkedin.com/in/adam-paul-0450561b9/");
      java.awt.Desktop.getDesktop().browse(uri);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Links to Chris' Linkedin when clicked.
   *
   * @param event - Opens link when it's clicked on
   */
  @FXML
  void chrisLinkedIn(ActionEvent event) {
    try {
      URI uri = new URI("https://www.linkedin.com/in/cfrank95/");
      java.awt.Desktop.getDesktop().browse(uri);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Links to Jaden's Linkedin when clicked.
   *
   * @param event - Opens link when it's clicked on
   */
  @FXML
  void jadenLinkedIn(ActionEvent event) {
    try {
      URI uri = new URI("https://www.linkedin.com/in/jaden-williams/");
      java.awt.Desktop.getDesktop().browse(uri);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Links to Joe's Linkedin when clicked.
   *
   * @param event - Opens link when it's clicked on
   */
  @FXML
  void joeLinkedIn(ActionEvent event) {
    try {
      URI uri = new URI("https://www.linkedin.com/in/morelli-j91/");
      java.awt.Desktop.getDesktop().browse(uri);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  ImageCipher imageCipher = new ImageCipher();

  /**
   * Prompts user to upload an image from their machine upon clicking the Upload Image button.
   *
   * @param mouseEvent - passed upon clicking on the Upload Image button in the pyctography tab
   */
  public void uploadImage(MouseEvent mouseEvent) throws IOException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Image File");
    Stage stage = (Stage) anchorPane.getScene().getWindow();
    File file = fileChooser.showOpenDialog(stage);

    if (file != null) { // only proceed, if file was chosen
      Image img = new Image(file.toURI().toString());
      imageDisplay.setImage(img);
      imageCipher.setOriginalImage(img);
    }
  }

  /**
   * On a button click, encrypts the image selected by the user via the uploadImage() method.
   *
   * @param mouseEvent - passed upon clicking on the Encrypt button in the pyctography tab
   */
  public void encryptImage(MouseEvent mouseEvent)
      throws FileNotFoundException, InterruptedException {
    // imageDisplay.imageProperty().setValue(null);

    Stage stage = new Stage();
    if (imageCipher.getOriginalImage() != null) { // only proceed, if file was chosen
      imageCipher.start(stage);
    }
  }

  /**
   * On a button click, decrypts the image selected by the user via the uploadImage() method.
   *
   * @param mouseEvent - passed upon clicking on the Decrypt button in the pyctography tab
   */
  public void decryptImage(MouseEvent mouseEvent) throws FileNotFoundException {
  }

}