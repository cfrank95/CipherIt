public abstract class Cipher implements CipherControl {

  @Override
  public  StringBuilder encrypt(String plainString) {
    return null;
  }

  @Override
  public  StringBuilder decrypt(String encryptedString) {
    return null;
  }
}

class Atbash extends Cipher implements CipherControl {

  @Override
  public  StringBuilder encrypt(String plainString) {

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphaLower = "abcdefghijklmnopqrstuvwxyz";

    int strLength = plainString.length();
    int alphLength = alphabet.length();
    StringBuilder cipher = new StringBuilder("");

    for (int i = 0; i < strLength; i++) {
      char b = plainString.charAt(i);
      if (b == ' ') {
        cipher.append(" ");
      }

      for (int j = 0; j < alphLength; j++) {
        char c = alphabet.charAt(j);
        char d = alphaLower.charAt(j);

        if (c == b) {
          int index = alphabet.indexOf(c);
          int position = (alphLength - 1) - index;
          cipher.append(alphabet.charAt(position));

        } else if (d == b) {
          int index = alphabet.indexOf(c);
          int position = (alphLength - 1) - index;
          cipher.append(alphaLower.charAt(position));
        }

      }

    }

    return cipher;

  }

  @Override
  public StringBuilder decrypt(String encryptedString) {

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphaLower = "abcdefghijklmnopqrstuvwxyz";

    int strLength = encryptedString.length();
    int alphLength = alphabet.length();
    StringBuilder decipher = new StringBuilder("");

    for (int i = 0; i < strLength; i++) {
      char b = encryptedString.charAt(i);
      if (b == ' ') {
        decipher.append(" ");
      }

      for (int j = 0; j < alphLength; j++) {
        char c = alphabet.charAt(j);
        char d = alphaLower.charAt(j);

        if (c == b) {
          int index = alphabet.indexOf(c);
          int position = (alphLength - 1) - index;
          decipher.append(alphabet.charAt(position));

        } else if (d == b) {
          int index = alphabet.indexOf(c);
          int position = (alphLength - 1) - index;
          decipher.append(alphaLower.charAt(position));
        }

      }

    }
    return decipher;

  }

}
