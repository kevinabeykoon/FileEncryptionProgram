import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Aleeza Ayaz, Kevin Abeykoon
 * Date: 02/27/23
 * Description: Using the first two codes, create a code that is user
 * friendly program to encrypt and decrypt codes that requires a
 * certain key number. Use four methods for this code to work:
 * Method 1: int key - Use a string method to ask for key code, and 
 * turn it into an integer so that it can be used to help the 
 * Encryption file code get the key codes
 * Method 2: boolean checkKey - Use an integer encryptKey to check if
 * the key codes given are valid to continue the program
 * Method 3: int putKeyInRange - Use the same encryptKey to change the
 * key code to match the numbers in the alphabet from -2 billion and 2
 * billion to -26 and 26. 
 * Method 4: int[] convertArray - use a string array keyCodes to 
 * format key codes for encryption file to use and encrypt/decrypt
 * given phrases with key codes
 * 
 */
public class UserInterfaceClass {




	// Method to get key from user
	public static void liveMode() {
		String status = "";
		do {
			String  output = "", option = "";
			String[] phrasesToOutput, sentences, inputFromFile, phrases, keyCodesUnformatted;

			int[] keyCodesFormatted;
			String temp = "";

			String phrase = JOptionPane.showInputDialog("Please enter a phrase. ");
			int encryptKey = Integer.parseInt((JOptionPane.showInputDialog("Please enter a key. ")));
			encryptKey = encryptKey - ((int) (encryptKey / 26)) * 26;

			do {
				option = JOptionPane.showInputDialog(null, "Would you like to encrypt (e) or decrypt (d)?");
			} while (!option.equalsIgnoreCase("e") && !option.equalsIgnoreCase("d"));


			output = Encryption.encodePhrase(phrase, encryptKey, option.charAt(0));

			// If statement to show user encrypted/decrypted phrases based on their choice
			if (option.equals("e")) {
				JOptionPane.showMessageDialog(null, "Here are the encrypted phrases:\n\n" + output);
			}
			else if (option.equals("d")) {
				JOptionPane.showMessageDialog(null, "Here are the decrypted phrases:\n\n" + output);
			}

			do {
				status = JOptionPane.showInputDialog(null, "Would you like to do another live conversion. Yes (y)? No (n)?");
			} while (!status.equalsIgnoreCase("y")&&!status.equalsIgnoreCase("n"));
		}while (status.equalsIgnoreCase("y"));
	}


	// Method to get key from user
	public static int key(String phrase) {
		phrase = JOptionPane.showInputDialog("Please enter Key code: ");
		int a = Integer.parseInt(phrase);
		return a;
	}

	// Method to check if key is valid for program
	public static boolean checkKey(int encryptKey) {

		if (encryptKey <= 2000000000 && encryptKey >= -2000000000 && encryptKey/26==0 ) {
			return true;
		}

		else {
			return false;
		}

	}

	// Method to change numbers from -2000000000 - +2000000000 to -26 - +26
	public static int putKeyInRange(int encryptKey) {
		encryptKey = encryptKey - ((int) (encryptKey / 26)) * 26;
		return encryptKey;
	}

	// Method to convert key code text file to help Encryption program to encrypt any phrases given
	public static int[] convertArray(String[] keyCodes) {
		int[] formattedKeyCodes = new int[keyCodes.length];

		for (int i = 0; i < formattedKeyCodes.length; i++) {
			if(checkKey(formattedKeyCodes[i])) {
				formattedKeyCodes[i] = putKeyInRange(Integer.parseInt(keyCodes[i]));
			}
			else {
				formattedKeyCodes[i] = putKeyInRange(Integer.parseInt(
						JOptionPane.showInputDialog(null, "Enter a key between -2 billion and +2 billion\n"
								+"\nRemember a multiple of 26 will not be accepted as it doesn't encode anything.")));
			}
		}

		return formattedKeyCodes;
	}

	public static void nonLiveMode() {
		String textFile="";
		String status = "";
		try {
			do {
				// Declare variables for code
				String  output = "", option = "";
				String[] phrasesToOutput, sentences, inputFromFile, phrases, keyCodesUnformatted;
				int encryptKey = 0;
				int[] keyCodesFormatted;
				String temp = "";

				// asks if the user would like to encrypt or decrypt
				do {
					option = JOptionPane.showInputDialog(null, "Would you like to encrypt (e) or decrypt (d)?");
				} while (!option.equalsIgnoreCase("e") && !option.equalsIgnoreCase("d"));

				// Gets the file name and use FileAccess method, loadFile, to read given file
				textFile = JOptionPane.showInputDialog(null, "Please enter text file: ", "KeyCodes.txt");
				inputFromFile = FileAccess.loadFile(textFile);

				// Use FileAccess method, getFirstElevenCharacters, to read given file to get the key codes
				keyCodesUnformatted = FileAccess.getFirstElevenCharacters(inputFromFile);

				//Formats the key codes in a way so that the Encrypt class can understand
				keyCodesFormatted = convertArray(keyCodesUnformatted);

				// Use FileAccess method, getPhrasesOnly, to read given file to get the phrases
				phrases = FileAccess.getPhrasesOnly(inputFromFile);

				// Uses Encryption method, encryptArray, to encrypt/decrypt given phrases using key codes
				phrasesToOutput = Encryption.encryptArray(phrases, keyCodesFormatted, option);

				// For loop to make a variable to output  encrypted/decrypted information
				for (int i = 0; i < phrasesToOutput.length; i++) {
					output += phrasesToOutput[i] + "\n";
				}

				// If statement to show user encrypted/decrypted phrases based on their choice
				if (option.equals("e")) {
					JOptionPane.showMessageDialog(null, "Here are the encrypted phrases:\n\n" + output);
				}
				else if (option.equals("d")) {
					JOptionPane.showMessageDialog(null, "Here are the decrypted phrases:\n\n" + output);
				}

				// Asks user if they want to save output shown
				do {
					temp = JOptionPane.showInputDialog(null, "Would you like to save these phrases to file? \nEnter 'y' for yes. Enter 'n' for no.");
				} while (!temp.equalsIgnoreCase("y") && !temp.equalsIgnoreCase("n"));

				// If yes, calls FileAccess method, saveFile, to save to file. 
				// It also asks the user for the output file's name
				if(temp.equalsIgnoreCase("y")){
					FileAccess.saveFile(JOptionPane.showInputDialog(null, "File Name?"), phrasesToOutput, keyCodesUnformatted);
				}

				//Asks the user if they'd want to use the program again.
				do {
					status = JOptionPane.showInputDialog(null,
							"Would you like to use the program again? \nEnter 'y' for yes. Enter 'n' for no.");
				} while (!status.equalsIgnoreCase("y") && !status.equalsIgnoreCase("n"));

			} while (status.equalsIgnoreCase("y"));

		}
		catch(FileNotFoundException error) {
			JOptionPane.showMessageDialog(null, textFile + "not Found!");
		}

		catch (NumberFormatException error) {
			JOptionPane.showMessageDialog(null, textFile + " is corrupted");
		}

		catch (NullPointerException error) {
			JOptionPane.showMessageDialog(null, "Operation cancelled.");
		}

		catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Unknown error." + error.toString());
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String textFile="", option = "";
		try{
			do {

				option = JOptionPane.showInputDialog(null,"Would you like to do live mode(1), or file input  mode (2)?");

			}while(!option.equals("1")&&!option.equals("2"));


			if(option.equals("1")){
				liveMode();
			}
			else if(option.equals("2")){
				nonLiveMode();
			}
		}


		catch (NumberFormatException error) {
			JOptionPane.showMessageDialog(null, textFile + " is corrupted");
		}

		catch (NullPointerException error) {
			JOptionPane.showMessageDialog(null, "Operation cancelled.");
		}

		catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Unknown error." + error.toString());
		}
	}
}
//END OF PROGRAM
