public class CipherLog {

  int logNumber = 1;
  String message = "";
  String cipherType = "";

  public CipherLog(String message, String cipherType) {
    this.message = message;
    this.cipherType = cipherType;

  }
  @Override
  public String toString() {
    String record =
        "Log #: " + logNumber + " --- " + "Message Ciphered: " + message + " --- " + "Cipher Type: "
            + cipherType;
    logNumber++;
    return record;
  }

  public int getLogNumber() {
    return logNumber;
  }

  public void setLogNumber(int logNumber) {
    this.logNumber = logNumber;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCipherType() {
    return cipherType;
  }

  public void setCipherType(String cipherType) {
    this.cipherType = cipherType;
  }


}
