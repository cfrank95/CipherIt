public interface CipherControl {

  StringBuilder encrypt(String plainString, int offset, String key);
  StringBuilder decrypt(String encryptedString, int offset, String key);

}
