public interface CipherControl {

  public StringBuilder encrypt(String plainString, int offset);
  public StringBuilder decrypt(String encryptedString);

}
