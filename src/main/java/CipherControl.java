/*---------------------------------------------------------
 file: CipherControl.java
   by: Adam Paul, Jaden Williams,
       Christopher Frank, Joseph Morelli
  for: Interface for initializing cipher methods
 ---------------------------------------------------------*/

/**
 * CipherControl interface initializes the two main methods that all cipher classes will share:
 * encrypt and decrypt.
 */
public interface CipherControl {

  /**
   * Encrypts a given string.
   *
   * @param plainString - String to encrypt
   * @param offset      - offset int for ciphers utilizing an offset
   * @param key         - key string for ciphers utilizing an encryption key
   * @return StringBuilder representation of the encrypted message
   */
  StringBuilder encrypt(String plainString, int offset, String key);

  /**
   * Decrypts a given string.
   *
   * @param encryptedString - String to decrypt
   * @param offset          - offset int for ciphers utilizing an offset
   * @param key             - key string for ciphers utilizing a decryption key
   * @return StringBuilder representation of the decrypted message
   */
  StringBuilder decrypt(String encryptedString, int offset, String key);

}
