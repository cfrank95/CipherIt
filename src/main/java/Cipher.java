public abstract class Cipher implements CipherControl {

  @Override
  public  StringBuilder encrypt(String plainString, int offset) {
    return null;
  }

  @Override
  public  StringBuilder decrypt(String encryptedString) {
    return null;
  }
}

class Atbash extends Cipher implements CipherControl {

  @Override
  public  StringBuilder encrypt(String plainString, int offset) {

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

class Caesar extends Cipher implements CipherControl {

  @Override
  public  StringBuilder encrypt(String plainString, int offset) {
    // StringBuilder to hold encrypted String
    StringBuilder encryptedString = new StringBuilder();

    // User inputs String to be encrypted and integer to offset characters


    System.out.println("Enter offset: ");

    // loop adding each ASCII character by the offset provided
    for(int i = 0; i < plainString.length(); i++){
      char letter = plainString.charAt(i);
      int letterInt = letter + offset;

      encryptedString.append((char)letterInt);
    }

    return encryptedString;
  }
}


