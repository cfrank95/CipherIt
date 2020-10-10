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
  public ChoiceBox offsetChoice;
  public TextField keyString;


  @FXML
  private TextField lblOutput;


  public void initialize() {
    choiceBoxSelect();
    offsetSelect();
  }

  /**
   * populates choice box on Product Line tab
   */
  public void choiceBoxSelect() {

    for (CipherType type : CipherType.values()) {
      choiceBox.getItems().add(type);
    }


  }

  /**
   * populates choice box on Product Line tab
   */
  public void offsetSelect() {
    for (int i = 1; i <= 5; i++) {
      offsetChoice.getItems().add(i);
    }


  }


  public void encrypt(MouseEvent mouseEvent) {
    String plainString = messageString.getText();
    CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

    switch (cipherType) {
      case Atbash:
        Cipher Atbash = new Atbash();
        lblOutput.setText(Atbash.encrypt(plainString, 0, null).toString());
      case Caesar:
        int offset = (int) offsetChoice.getValue();
        Cipher Caesar = new Caesar();
        lblOutput.setText(Caesar.encrypt(plainString, offset, null).toString());
      case Vigenere:
        String key = keyString.getText();
        Cipher Vigenere = new Vigenere();
        lblOutput.setText(Vigenere.encrypt(plainString, 0, key).toString());
    }

  }

  public void decrypt(MouseEvent mouseEvent) {
    String messString = messageString.getText();
    CipherType cipherType = CipherType.valueOf(choiceBox.getValue().toString());

    switch (cipherType) {
      case Atbash:
        Cipher Atbash = new Atbash();
        lblOutput.setText(Atbash.decrypt(messString, 0, null).toString());
      case Caesar:
        int offset = (int) offsetChoice.getValue();
        Cipher Caesar = new Caesar();
        lblOutput.setText(Caesar.decrypt(messString, offset, null).toString());
      case Vigenere:
        String key = keyString.getText();
        Cipher Vigenere = new Vigenere();
        lblOutput.setText(Vigenere.decrypt(messString, 0, key).toString());
    }
  }
}