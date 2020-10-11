/*---------------------------------------------------------
 file: Ciphers.java
   by: Adam Paul, Jaden Williams,
       Christopher Frank, Joseph Morelli
  for: File containing all cipher classes used in our app
 ---------------------------------------------------------*/

/**
 * Ciphers class from which all ciphers inherit. Contains default methods for encrypt and decrypt.
 */
public abstract class Ciphers implements CipherControl {

  /**
   * Default encrypt method
   *
   * @return null
   */
  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {
    return null;
  }

  /**
   * Default decrypt method
   *
   * @return null
   */
  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {
    return null;
  }
}

/**
 * Class for Atbash type ciphers. Effectively flips the alphabet over. A -> Z, B -> Y, C -> X, etc.
 */
class Atbash extends Ciphers implements CipherControl {

  /**
   * Takes an input string and encrypts it by flipping the alphabet.
   *
   * @param plainString - phrase to encrypt
   * @param offset      - irrelevant to encryption method
   * @param key         - irrelevant to encryption method
   * @return the encrypted message
   */
  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {

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

  /**
   * Takes an input string and decrypts it by flipping the alphabet.
   *
   * @param encryptedString - phrase to decrypt
   * @param offset          - irrelevant to decryption method
   * @param key             - irrelevant to decryption method
   * @return the decrypted message
   */
  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {

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

/**
 * Class for Caesar type ciphers. Shifts each letter of the alphabet over by a specified offset. For
 * example, with offset 3, A -> D, B -> E, C -> F, etc.
 */
class Caesar extends Ciphers implements CipherControl {

  /**
   * Takes an input string and encrypts it by shifting the ASCII code by a specified offset.
   *
   * @param plainString - phrase to encrypt
   * @param offset      - specifies how many letters each letter will be offset by
   * @param key         - irrelevant to encryption method
   * @return the encrypted message
   */
  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {
    StringBuilder encryptedString = new StringBuilder();

    for (int i = 0; i < plainString.length(); i++) {
      if (Character.isUpperCase(plainString.charAt(i))) {
        char ch = (char) (((int) plainString.charAt(i) +
            offset - 65) % 26 + 65);
        encryptedString.append(ch);
      } else if (plainString.charAt(i) == ' ') {
        encryptedString.append(" ");

      } else {
        char ch = (char) (((int) plainString.charAt(i) +
            offset - 97) % 26 + 97);
        encryptedString.append(ch);
      }
    }
    return encryptedString;
  }

  /**
   * Takes an input string and decrypts it by shifting the ASCII code by a specified offset.
   *
   * @param encryptedString - phrase to decrypt
   * @param offset          - specifies how many letters each letter will be offset by
   * @param key             - irrelevant to decryption method
   * @return the decrypted message
   */
  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {

    // StringBuilder to hold encrypted String
    StringBuilder decryptedString = new StringBuilder();

    // loop adding each ASCII character by the offset provided
    for (int i = 0; i < encryptedString.length(); i++) {
      char letter = encryptedString.charAt(i);

      int letterInt = letter - offset;

      if (letter <= 32) {
        decryptedString.append(" ");
      } else if (letterInt < 65 && letterInt > 32) {
        letterInt = 122 - (64 - letterInt);
        decryptedString.append((char) letterInt);
      } else if (letterInt < 97 && letterInt > 90) {
        letterInt = 90 - (96 - letterInt);
        decryptedString.append((char) letterInt);
      } else {
        decryptedString.append((char) letterInt);
      }
    }

    return decryptedString;

  }
}


/**
 * Class for Vigenere type ciphers.  This encryption key also doubles as the key to decrypt the
 * encrypted string.
 */
class Vigenere extends Ciphers implements CipherControl {

  /**
   * Takes an input string and a key string and encrypts the input string using the key string.
   *
   * @param plainString - phrase to encrypt
   * @param offset      - irrelevant to encryption method
   * @param key         - string used to encrypt plainString
   * @return the encrypted message
   */
  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {

    StringBuilder newString = new StringBuilder();                //Final encrypted string
    int passChar;                         //Pass index value
    int passIndex = -1;                    //Pass array value
    int divisibility = 1;                 //How many times the alphabet repeats

    //log.setTextLog("Encryption length = " + key.length());

    for (int i = 0; i < plainString.length(); i++) {
      char tempChar = (char) (plainString.charAt(i) - 96);   //phrase index character
      char newChar;                       //Temp character

      if (passIndex >= key.length() - 1) //Does encrypted password index need to be looped?
      {
        passIndex = 0;
      } else {
        passIndex++;
      }

      System.out.println("Encryption index = " + passIndex);
      passChar =
          (int) key.charAt(passIndex) - 96;               //Encrypted char = Encrypted char at index

      if (plainString.charAt(i)
          != ' ') {                                            //If phrase character is not a space,
        System.out.println(
            (char) (tempChar + 96) + "," + (int) tempChar + " + " + (char) ((int) passChar + 96)
                + "," + passChar + " = " + (char) ((int) tempChar + passChar + 96) + "," + (
                (int) tempChar + passChar));
        if ((tempChar + passChar)
            > 25) {                              //If phrase char and encryption char are over alphabet length,
          divisibility = ((tempChar + passChar)
              / 26);                  //How many times does the alphabet repeats
          System.out.println(
              divisibility + " = divisible");              //How many times the alphabet is repeated
          newChar = (char) Math.abs((tempChar + passChar) - (25 * divisibility)
              + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
        } else {                                                         //Else if phrase is within alphabet range,
          newChar = (char) (tempChar + passChar + 96);
        }
      } else {
        newChar = ' ';
      }

      newString.append(newChar);

      //System.out.println(newChar);
    }
    return newString;
  }

  /**
   * Takes an input string and a key string and decrypts the input string using the key string.
   *
   * @param encryptedString - phrase to decrypt
   * @param offset          - irrelevant to decryption method
   * @param key             - string used to decrypt encryptedString
   * @return the decrypted message
   */
  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {

    StringBuilder newString = new StringBuilder();   //Final encrypted string
    int passChar;                         //Pass index value
    int passIndex = -1;                    //Pass array value
    int divisibility = 1;                 //How many times the alphabet repeats

    //System.out.println("Encryption length = " + phraseEncrypter.length());

    for (int i = 0; i < encryptedString.length(); i++) {
      char tempChar = (char) (encryptedString.charAt(i) - 96);   //phrase index character
      char newChar;                       //Temp character

      if (passIndex >= key.length() - 1) //Does encrypted password index need to be looped?
      {
        passIndex = 0;
      } else {
        passIndex++;
      }

      System.out.println("Encryption index = " + passIndex);
      passChar =
          (int) key.charAt(passIndex) - 96;               //Encrypted char = Encrypted char at index

      if (encryptedString.charAt(i)
          != ' ') {                                            //If phrase character is not a space,
        System.out.println(
            (char) (tempChar + 96) + "," + (int) tempChar + " - " + (char) ((int) passChar + 96)
                + "," + passChar + " = " + (char) ((int) tempChar - passChar + 96) + "," + (
                (int) tempChar - passChar));
        if ((tempChar - passChar) > 0 && (tempChar - passChar)
            < 25) {                              //If phrase char and encryption char are over alphabet length,
          newChar = (char) (tempChar - passChar + 96);
          //divisibility = ((tempChar - passChar) / -26);                  //How many times does the alphabet repeats
          //System.out.println(divisibility + " = divisible");              //How many times the alphabet is repeated
          //newChar = (char) Math.abs((tempChar - passChar) - (25 * divisibility) + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
        } else {                                                         //Else if phrase is within alphabet range,
          if ((tempChar - passChar) < 0 && (tempChar - passChar) > -25) {
            newChar = (char) Math.abs(25 + (tempChar - passChar)
                + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
          } else if ((tempChar - passChar) < -26) {
            divisibility = ((tempChar - passChar)
                / -26);                  //How many times does the alphabet repeats
            System.out.println(divisibility
                + " = divisible");              //How many times the alphabet is repeated
            newChar = (char) Math.abs((tempChar - passChar) - (25 * divisibility)
                + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
          } else {
            newChar = '*';
          }
        }
      } else {
        newChar = ' ';
      }

      newString.append(newChar);

      //System.out.println(newChar);
    }
    return newString;
  }

}

/**
 * Class for Numeric type ciphers. Assigns a numeric value to every character in the input string,
 * with numeric values being broken up by spaces. Ex. A -> 1, B -> 2, C -> 3 . . .  Y -> 25, Z ->
 * 26.
 */
class Numeric extends Ciphers implements CipherControl {

  /**
   * Takes an input string and encrypts it by replacing alphabetical values with their associated
   * numerical values.
   *
   * @param plainString - phrase to encrypt
   * @param offset      - irrelevant to encryption method
   * @param key         - irrelevant to encryption method
   * @return the encrypted message
   */
  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {
    String tempInt = "";
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < plainString.length(); i++) {
      if (plainString.charAt(i) != ' ') {
        tempInt = String.valueOf((int) plainString.charAt(i) - 96);
        if (i == 0) {
          result.append("" + (tempInt));
        } else {
          result.append(" " + (tempInt));
        }
      }
    }

    return result;
  }

  /**
   * Takes an input string and decrypts it by replacing numerical values with their associated
   * alphabetical values.
   *
   * @param encryptedString - phrase to decrypt
   * @param offset          - irrelevant to decryption method
   * @param key             - irrelevant to decryption method
   * @return the decrypted message
   */
  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {
    String tempLetter = " ";
    int stringConverter = 0;
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < encryptedString.length(); i++) {
      if (encryptedString.charAt(i) != ' ') {
        if (encryptedString.charAt(i) != ' ' && i < encryptedString.length() - 1
            && encryptedString.charAt(i + 1) != ' ') {
          stringConverter =
              (Integer.parseInt(String.valueOf(encryptedString.charAt((i)))) * 10) + Integer
                  .parseInt(String.valueOf(encryptedString.charAt((i + 1))));
          System.out.println("Interger = " + stringConverter);
          tempLetter = "" + (char) (stringConverter + 96);
          i++;
        } else {
          char tempChar = (char) (encryptedString.charAt(i) + 48);
          tempLetter = "" + tempChar;
        }

        if (i == 0) {
          result.append("" + (tempLetter));
        } else {
          result.append("" + (tempLetter));
        }
      }
    }
    return result;
  }
}



