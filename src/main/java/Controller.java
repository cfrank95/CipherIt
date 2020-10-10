import java.util.Scanner;
import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Controller {

  static Scanner keyboard = new Scanner(System.in);
  public TextField messageString;
  public ChoiceBox choiceBox;


  @FXML

  private TextField lblOutput;


  public void initialize() {
    choiceBoxSelect();
  }

  /**
   * populates choice box on Product Line tab
   */
  public void choiceBoxSelect() {

    for (CipherType type : CipherType.values()) {
      choiceBox.getItems().add(type);
    }


  }


  public void encrypt(MouseEvent mouseEvent) {
    String plainString = messageString.getText();
    CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

    switch (cipherType) {
      case Atbash:
        Cipher Atbash = new Atbash();
        lblOutput.setText(Atbash.encrypt(plainString).toString());
      case Caesar:

    }

  }

  public void decrypt(MouseEvent mouseEvent) {
    String plainString = messageString.getText();
    CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

    switch (cipherType) {
      case Atbash:
        Cipher Atbash = new Atbash();
        lblOutput.setText(Atbash.decrypt(plainString).toString());
      case Caesar:
    }
  }
}