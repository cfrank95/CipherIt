import java.awt.event.ActionEvent;
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
  public Label offsetLbl;
  public Label keyPhraseLabel;


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
      choiceBox.getSelectionModel().selectFirst();
    }


  }

  /**
   * populates choice box on Product Line tab
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


  public void typeSelect(javafx.event.ActionEvent actionEvent) {
    offsetToggle();
  }
}