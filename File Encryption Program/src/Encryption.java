/*
 * Author: Kevin Abeykoon
 * Description: This class takes a phrase as input, and encypts or decrypts it. It then sends it back as a String.
 * Methods:
 * 
 * 			1. public static boolean isNotALetter(char character) - Parameter is a char. Method will check if char is 
 * 																	a letter and will return false if it is a letter.
 * 
 * 			2. public static char encode(char letter, int key) - Parameters are a letter as a char and a key as a integer.
 * 																 Method will encode the letter using the key. If a key of 1
 * 																 is inputed, the program will return a the letter that is one
 * 																 place ahead of the entered letter.
 * 
 * 			3. public static char decode (char letter, int key) - Parameters are a letter as a char and a key as a integer.
 * 																 Method will decode the letter using the key. If a key of 1
 * 																 is inputed, the program will return a the letter that is one
 * 																 place before the entered letter.
 * 			4. public static String[] encryptArray(String[] phrases, int keyCodes[], String option)
 																- Parameters are phrases and keyCode arrays. and option to
 * 																 encode or decode. Method will return an ecrypted array
 * 			5. public static String encodePhrase(String inputPhrase, int key, char option)
 * 																- Parameters are phrases (String) and keyCode (int). 
 * 																and option to
 * 																 encode or decode. Method will return an ecrypted String
 * 			6. public static void main(String[] args) - The main method. It is only used for self-testing. 
 */
public class Encryption {

	/*
	 * Method to check whether a character is a letter. Returns false if the
	 * character is a letter
	 */
	public static boolean isNotALetter(char character) {

		// converts char variable to int
		int characterInt = (int) character;
		
		//checks if letter
		if (Character.isLetter(character)) {
			return false;
		}

		return true;
	}

	/*
	 * Method that encodes a character based on a key. Returns the encoded character
	 * as a char.
	 */
	public static char encode(char letter, int key) {
		//Declare and initilize variables
		int additionAmount = 0, letterInt = (int) letter;
		int max = 0, min = 0;
		int difference = 0;

		//sets the ranges based on the case
		if (!Character.isUpperCase(letterInt)) {
			max = 122;
			min = 97;
		} else if (Character.isUpperCase(letterInt)) {
			max = 90;
			min = 65;
		}

		//if a letter, determines the new letter
		if (65 <= letterInt && letterInt <= 90 || 96 <= letterInt && letterInt <= 122) {
			additionAmount = key - ((int) (key / 26)) * 26;
			letterInt = (int) letter + additionAmount;
			if (letterInt > max) {
				difference = letterInt - max;
				letterInt = min + difference - 1;

			} else if (letterInt < min) {
				letterInt = (int) letter + additionAmount;
				difference = min - letterInt;
				letterInt = max - difference + 1;
			}

		}

		return (char) letterInt;
	}

	/*
	 * Method that decodes a character based on a key. Returns the decoded character
	 * as a char.
	 */
	public static char decode(char letter, int key) {
		char a = (encode(letter, -key));
		return a;
	}

	/*
	 * Method that encodes a array based on a key. Returns the encoded array
	 */
	public static String[] encryptArray(String[] phrases, int keyCodes[], String option) {
		//declaring output array
		//Encrypts the entire array
		String encodedPhrases[] = new String[phrases.length];
		for (int i = 0; i < encodedPhrases.length; i++) {
			encodedPhrases[i] = Encryption.encodePhrase(phrases[i], keyCodes[i], option.charAt(0));
		}
		return encodedPhrases;

	}

	
	/*
	 * Method that encodes a String based on a key. Returns the encoded string
	 */
	public static String encodePhrase(String inputPhrase, int key, char option) {
		//declaring output variable
		String outputPhrase = "";

		//encodes
		for (int i = 0; i < inputPhrase.length(); i++) {
			if (option == 'e' || option == 'E') {
				outputPhrase += encode(inputPhrase.charAt(i), key);
			} else {
				outputPhrase += decode(inputPhrase.charAt(i), key);
			}
		}
		return outputPhrase;
	}


	/*
	 * Main method. Used for testing.
	 */

	public static void main(String[] args) {


		//isNotALetter() TEST
		System.out.println(isNotALetter('e'));
		System.out.println(isNotALetter('9'));

		//encode() TEST
		System.out.println(encode('e', 26));
		System.out.println(encode('a', 1));
		System.out.println(encode('a', 27));

		//decode() TEST
		System.out.println(decode('e', 26));
		System.out.println(decode('a', 1));


		//encryptArray() TEST
		String[] phrases = {"dog jumping!", "cat meowing"};
		int[] keyCodes = {1, 3};
		String option = "e";
		String output[] = encryptArray( phrases, keyCodes, option);
		for(int i =0; i<output.length; i++) {
			System.out.println(output[i]);
		}
	}

}
