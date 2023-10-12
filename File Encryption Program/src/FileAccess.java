import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



/**
 * @author Nishaaj

 * Date: March 2nd,  2023
 * 
 * Description: This accessing class serves the purposes of File Access. And contains 
 * a total of 3 methods to do so.
 * 
 * Method 1, loadFile: This method takes the fileName as the parameter
 * and returns an array with all the lines from the file it reads from excluding the first line
 * .Before the method finishes, it closes the 
 * 
 * 
 * Method 2, saveFile: This method writes the original writes data into the file name provided, 
 * and writes the length of the String array to the first line of the file. 
 * Then, the method loops through the String array, writes each element of the array to the file, 
 * followed by new lines everytime. Than it  closes the output writer. Finally, returns the original contents of the String array.
 * so it can be read back by the read method later
 *  
 *  Method 3, getPhrasesOnly: This method returns a string array with only the phrases found in the inputFile on separate lines.
 *  
 *  Method 4, getKeysOnly: This method returns a string array with only the key codes found in the inputFile on separate lines
 */ 

public class FileAccess {

	/**
	 *method to load from a file given the file name which is the parameter
	 *returns a String array which excludes the first line in the file it reads from
	 */



	public static String[] loadFile(String fileName) throws IOException {
		// open the file to read from
		FileReader fr = new FileReader(fileName);
		BufferedReader input = new BufferedReader(fr);



		// read the size of file located on the first line and save it in size
		int size = Integer.parseInt(input.readLine());

		// declare and create a String array
		String[] fileInfo = new String[size];

		// read the rest of the files
		for (int i = 0; i < size; i++) {
			// read each line into the array
			fileInfo[i] = input.readLine();
		}

		input.close(); // close not file but its file string

		return fileInfo; // return String array
	}

	/**
	 * This method  and writes a String array to a new file
	 * 
	 */
	public static void saveFile(String fileName, String[] phrases, String[] keyCodes) throws IOException {


		FileWriter fw = new FileWriter(fileName); // Creates a new file writer for the given fileName
		BufferedWriter output = new BufferedWriter(fw); // writes data into fileName provided


		// This console output prints a title for what the output file contains which includes the 
		// first line of. Its placed in this method to keep output organized when used in main method 
	//	System.out.println("\nOutput File Output:");
		//System.out.println(phrases.length);

		// output.write(Integer.toString(phrases.length));
		//  output.newLine();

		output.write(phrases.length+"\n"); //Write the length of the phrases array to the outputfile. This is the first line 

		// Loop through the phrases array followed by a new line each time to the file.
		for (int i = 0; i < phrases.length; i++) {

				output.write(keyCodes[i]+phrases[i]);

			output.newLine();
		}

		output.close(); //close the output writer. If this is not closes this may cause generation issues to the new file


	}

	//public static String[] giveKeys (String [] phrases)  {

	//creating an array 




	//	}

	// This method takes in a string array which should be the array which contains just the phrases
	// and returns only the keys from each phrase 

	public static String[] getFirstElevenCharacters(String[] phrases) {

		// Create a new array to hold the results which has the same length as the input array (one placed in the parameter of method)
		String[] result = new String[phrases.length];

		// Loops through array, skipping the first line
		for (int i = 0; i < phrases.length; i++) {


			// for each string in the array it gets the first 11 characters using the substring method and stores 
			//the result in the string called firstEleven 


			//this line of code takes a substring of the input string at 
			//index i from  position 0 up to 
			//index 11 but does not include it. IT than assigns it to the variable firstElevenCharacters. 
			// The resulting string will contain the first 11 characters of the original string at index i.
			String firstElevenCharacters = phrases[i].substring(0, 11);  

			// Add the string firstElevenCharacters to the current index of the result array.
			result[i] = firstElevenCharacters;
		}

		return result; //returns result which is a String array with all key codes at separate indexes
	}


	//getPhrasesOnly takes in string array for phrases
	public static String [] getPhrasesOnly (String [] phrases) {

		String [] result = new String[phrases.length];
		for (int i = 0; i < phrases.length; i++) {

			String justPhrases = phrases[i].substring(11); // only takes characters after the 11th one in each index/line

			result[i] = justPhrases; // store string justPhrases into the result array each time 


		}

		return result; // return result which is a String array with all the phrases at separate indexes


	}

	//self testing main method

	public static void main(String[] args) throws IOException {

		System.out.println("Input file output:");



		//////////////////////////////   Testing loadFile method      ////////////////////////////////////////////

		String inputFile[] = FileAccess.loadFile("Untitled 4"); 

		// using the load file method to read the contents of the file which returns a string array. Save this
		// into the inputFile  String [] array. This now contains the all lines in the text file excluding the 
		// size of the array which was the first line.



		//display the content of the file 
		for (int i = 0; i < inputFile.length; i++) {
			System.out.println(inputFile[i]);
		}


		/////////////////////////   Testing the getFirstElevenCharacters method ///////////////////////////////////

		System.out.println("\nkeys:" ); // print keys to the console so we can easily understand what this method returns

		String [] keys = FileAccess.getFirstElevenCharacters(inputFile); 

		// Again use the inputFile String array 
		// which contains the all lines in the text file excluding the 
		// size of the array which was the first line. The method contains a loop which only extracts the first 11 characters
		// of each line which represents the key in each line. String [] keys array will now contain the result array which this method
		//returns


		//loops through all lines in []keys which only contains the encryption codes. 

		for (int i=0; i < keys.length; i++) {
			System.out.println(keys[i]); //prints each key on a new line
		}


		//testing getPhrasesOnly method 
		
		System.out.println("\nPhrases only:" );

		String [] phrases = FileAccess.getPhrasesOnly(inputFile); // call method using the inputFile. 
		
		
        // loop through length of phrases of each index to display only phrases for each line in inputFile
		for (int i=0; i < phrases.length; i++) {

			System.out.println(phrases[i]);

		}
		
		//testing output File output
		
		System.out.println("\nOutputFile output"); // print to console
		
		System.out.println(phrases.length); // print length of file
		
		String outputFile = "output.txt"; // declare string variable for output.txt which is the file we will write to
		
		FileAccess.saveFile(outputFile, phrases,keys); // write phrases and keys arrays to "output.txt" by calling method
		
		String [] outputFileContents = FileAccess.loadFile(outputFile); // use load file method to store analyzed written data
		
		for (int i = 0; i < outputFileContents.length; i++) { // loop through and print each line to ensure that data has been written correctly
			System.out.println(outputFileContents[i]);
		}


	}
}

//END OF PROGRAM
