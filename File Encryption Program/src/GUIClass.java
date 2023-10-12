import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/*
 * Author: Kevin Abeykoon
 * Date: Feb 13, 2023
 
 * Description: Using the first two codes, create a code that is user
 * friendly program to encrypt and decrypt codes that requires a
 * certain key number. Use four methods for this code to work:
 * 
 * 
 * Method 1: int key - Use a string method to ask for key code, and 
 * 			turn it into an integer so that it can be used to help the 
 * 			Encryption file code get the key codes
 * Method 2: boolean checkKey - Use an integer encryptKey to check if
 * 				the key codes given are valid to continue the program
 * Method 3: int putKeyInRange - Use the same encryptKey to change the
 * 			key code to match the numbers in the alphabet from -2 billion and 2
 * 			billion to -26 and 26. 
 * Method 4: int[] convertArray - use a string array keyCodes to 
 * 			format key codes for encryption file to use and encrypt/decrypt
 *			 given phrases with key codes 
 * 
 */
public class GUIClass extends JFrame implements ActionListener{

	//Declaring and initializing labels
	JLabel title = new JLabel("Encryption Tool");
	JLabel inputFileLabel = new JLabel("What's the input file name?");
	JLabel outputFileLabel = new JLabel("What's the output file name when saving?");
	JLabel optionLabel = new JLabel("Would you like to encrypt or decrypt?");



	//Declaring and initializing buttons
	JButton enterButton = new JButton("Enter");
	JButton exitButton = new JButton("Exit");
	JButton printButton = new JButton("Print");

	//Declaring and initializing fields
	JTextField inputFileField = new JTextField();
	JTextField outputFileField = new JTextField();

	String[] option = { "Encrypt", "Decrypt"};
	JComboBox optionList = new JComboBox(option);

	String[] phrasesToPrint;
	String[] keyCodesUnformattedToPrint;

	// Method to get key from user
	public static int key(String phrase) {
		phrase = JOptionPane.showInputDialog("Please enter Key code: ");
		int a = Integer.parseInt(phrase);
		return a;
	}

	// Method to check if key is valid for program
	public static boolean checkKey(int encryptKey) {

		if (encryptKey <= 2000000000 && encryptKey >= -2000000000) {
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
			formattedKeyCodes[i] = putKeyInRange(Integer.parseInt(keyCodes[i]));

		}

		return formattedKeyCodes;
	}


	/*
	 * Constructor 
	 */
	public GUIClass() {
		//Setting up frame
		super("Ecryption Tool");
		setSize(500,310);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		//Setting up title font and adding it to the frame
		Font titleFont = new Font("Sans Serif", Font.BOLD, 20);
		title.setFont(titleFont);
		title.setBounds(10,10,400,30);
		add(title);

		//Setting bounds and adding labels to frame
		inputFileLabel.setBounds(10,50,250,20);
		add(inputFileLabel);
		outputFileLabel.setBounds(10,90,250,20);
		add(outputFileLabel);
		optionLabel.setBounds(10,130,250,20);
		add(optionLabel);

		//Setting bounds and adding fields to frame
		inputFileField.setBounds(270,50,100,20);
		add(inputFileField);
		outputFileField.setBounds(270,90,100,20);
		add(outputFileField);
		optionList.setBounds(270,130,100,20);
		add(optionList);

		//Setting bounds and adding buttons to frame
		enterButton.addActionListener(this);
		enterButton.setBounds(50,210,100,20);
		add(enterButton);
		printButton.addActionListener(this);
		printButton.setBounds(160,210,200,20);
		add(printButton);
		exitButton.addActionListener(this);
		exitButton.setBounds(370,210,100,20);
		add(exitButton);

		//Setting how frame closes and setting frame visible
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}



	/*
	 * Method to deal with button events
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			//Enter button
			if(enterButton==e.getSource()) {

				// Declare variables for code
				String textFile, output = "", option = "";
				String[] phrasesToOutput, sentences, inputFromFile, phrases, keyCodesUnformatted;
				int encryptKey = 0;
				int[] keyCodesFormatted;
				String temp = "";

				option = (((String)optionList.getSelectedItem()).substring(0,1)).toLowerCase();


				// Gets the file name and use FileAccess method, loadFile, to read given file
				textFile = inputFileField.getText();

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


				phrasesToPrint = phrasesToOutput;
				keyCodesUnformattedToPrint=keyCodesUnformatted;



			}

			//Exit button. Closes frame.
			if(exitButton==e.getSource()) {
				this.dispose();
			}

			//View button to view past entries.
			if(printButton==e.getSource()) {
				String textFile = outputFileField.getText();
				FileAccess.saveFile(textFile, phrasesToPrint, keyCodesUnformattedToPrint);

				//Outputs message
				JOptionPane.showMessageDialog(null, "File Printed!");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * The main method
	 */
	public static void main(String[] args) {
		// Calls the constructor to create a window.
		new GUIClass();	
	}

}
//END OF PROGRAM
