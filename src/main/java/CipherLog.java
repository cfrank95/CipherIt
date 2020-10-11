/*---------------------------------------------------------
 file: CipherLog.java
   by: Adam Paul, Jaden Williams,
       Christopher Frank, Joseph Morelli
  for: Class for logging cipher encrypts/decrypts
 ---------------------------------------------------------*/

/**
 * Class CipherLog concerns the "Log" TextArea in the cryptography class. This class implements
 * basic functionality for the Log text field.
 */
public class CipherLog {

  int logNumber = 1;
  String message = "";
  String cipherType = "";

  /**
   * CipherLog constructor
   *
   * @param cipherType - type of cipher last used
   * @param message    - message last encrypted or decrypted
   */
  public CipherLog(String message, String cipherType) {
    this.message = message;
    this.cipherType = cipherType;
  }

  /**
   * Prints CipherLog object.
   *
   * @return the formatted string displaying the CipherLog object's information
   */
  @Override
  public String toString() {
    String record =
        "Log #: " + logNumber + " --- " + "Message Ciphered: " + message + " --- " + "Cipher Type: "
            + cipherType;
    logNumber++;
    return record;
  }

  /**
   * getter for log number
   *
   * @return current log number
   */
  public int getLogNumber() {
    return logNumber;
  }

  /**
   * setter for log number
   *
   * @param logNumber - new value of logNumber being passed in
   */
  public void setLogNumber(int logNumber) {
    this.logNumber = logNumber;
  }

  /**
   * getter for message
   *
   * @return current message being encrypted/decrypted
   */
  public String getMessage() {
    return message;
  }

  /**
   * setter for message
   *
   * @param message - new value of message being passed in
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * getter for cipher type
   *
   * @return current cipher type
   */
  public String getCipherType() {
    return cipherType;
  }

  /**
   * setter for cipher type
   *
   * @param cipherType - new value of cipherType being passed in
   */
  public void setCipherType(String cipherType) {
    this.cipherType = cipherType;
  }


}
