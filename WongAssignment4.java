import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WongAssignment4 {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter your file path: ");
		String filePath = kb.nextLine();
		Scanner inputStream = null;

		try {
			inputStream = new Scanner(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {// ends program if error
			System.out.println("File " + filePath + " was not found");
			System.out.println("or could not be opened.");
			System.exit(0);
		}
		kb.close();// closes kb which was used for input

		String toFileString = "";
		while (inputStream.hasNextLine()) {
			Scanner readFile = new Scanner(inputStream.nextLine());
			while (readFile.hasNext()) {
				String StrSpace = readFile.next();
				toFileString = toFileString + StrSpace + " "; // spaces out the
																// file
																// appropriately
			}
			readFile.close();
			toFileString = toFileString + System.lineSeparator();// gives a new
																	// line if
																	// one is
																	// made
		}
		File tempFile = null;
		try {
			tempFile = File.createTempFile("temporary1", ".txt"); // creates
																	// temporary
			// file
			boolean deleteStatus = false; // done to work with the file booleans
			// System.out.println(tempFile.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));// used
																				// in
																				// order
																				// to
																				// make
																				// the
																				// temp
																				// files
			bw.write(toFileString);
			bw.close();
			File originalFile = new File(filePath);
			if (originalFile.exists()) {
				deleteStatus = originalFile.delete();
			}
			deleteStatus = originalFile.createNewFile();
			try {
				PrintWriter writer = new PrintWriter(filePath, "UTF-8");
				writer.print(toFileString);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// tempFile.deleteOnExit();// deletes file when the virtual machine
			// terminate
		} catch (IOException e) {
			e.printStackTrace(); // if any error occurs
		}
		System.out.println("Your file is rewritten");
		inputStream.close();
	}
}
// C:/Users/WONG_CHRI/Documents/CS 280/Text files/Source
// Code_6e/ch10/morestuff2.txt
