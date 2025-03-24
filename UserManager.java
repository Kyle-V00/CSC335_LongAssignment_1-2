/*
 * Class: 	UserManager
 * Author: 	Liam Mohajeri Norris & Kyle Velasco
 * Purpose: Manage multiple LibraryModel objects that
 * 			represent different users' libraries, as well as a
 * 			list of users and a file containing their encrypted
 * 			passwords.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class UserManager {

	private File userFile;
	private Hashtable<String, LibraryModel> users;
	private MessageDigest encrypt;
	
	public UserManager() throws IOException, NoSuchAlgorithmException {
		// Build the userFile File
		this.userFile = new File("users.txt");
		this.users = new Hashtable<String, LibraryModel>();
		try {
			if (!this.userFile.createNewFile()) {
				this.userFile.delete();
				this.userFile.createNewFile();
			}
		} catch (IOException e) {
			System.err.println("Error instantiating user database");
		}
		this.encrypt = MessageDigest.getInstance("SHA-256");
	}
	
	public boolean addUser(String username) {
		/*
		 * boolean addUser(String username)
		 * Purpose: Create a user account by initializing
		 * 			a new LibraryModel and adding it to the hash table.
		 * Input:	String username		name of new user
		 * Output:	true if user added, false if user already exists
		 */
		
		if (users.containsKey(username)) {
			return false;
		}
		else {
			LibraryModel lib = new LibraryModel();
			users.put(username, lib);
			return true;
		}
	}
	
	public boolean addPassword(String username, String password) throws NoSuchAlgorithmException {
		/*
		 * boolean addPassword(String username, String password) throws NoSuchAlgorithmException
		 * Purpose: Save a hashed, salted password for a new user in the userFile file
		 * Input:	String username		name of new user
		 * 			String password		password to encrypt and save
		 * Output:	true if password added, false if error encountered
		 */

		encrypt.update(password.getBytes());
		byte[] hash = encrypt.digest();
		
		try (BufferedWriter w = Files.newBufferedWriter(userFile.toPath(), APPEND)) {
			w.append(username + " ");
			for (int i = 0; i < hash.length; i ++) {
				w.append(String.valueOf(hash[i]));
			}
			w.append("\n");
			return true;
		}
		catch (IOException x) {
			System.err.println(x);
			return false;
		}
	}
	
	public boolean login(String username, String password, boolean noTest) throws FileNotFoundException {
		/*
		 * boolean login(String username, String password) throws FileNotFoundException
		 * Purpose: Validate username and password. Access the LibraryModel 
		 * 			associated with the given user. Feed the LibraryModel to View to
		 * 			enable user interaction with their library.
		 * Input:	String username		name of new user
		 * 			String password		password to encrypt and save
		 * 			boolean noTest		used for avoiding infinite runtime when testing
		 * Output:	true if username and password match records. Return false otherwise
		 */
		
		// Structure: Call View and feed it a LibraryModel from the hash table
		
		Scanner s = new Scanner(userFile);
		
		// Check if the user exists
		while (s.hasNextLine()) {
			String[] line = s.nextLine().split(" ");
			if (line[0].equals(username)) {
				line[1] = line[1].strip();
				// Check the hashed entered password against
				// the stored digest
				encrypt.reset();
				encrypt.update(password.getBytes());
				byte[] hash = encrypt.digest();
				
				String encryptedString = "";
				for (int i = 0; i < hash.length; i ++) {
					encryptedString += String.valueOf(hash[i]);
				}
	
				if (encryptedString.equals(line[1])) {
					System.out.println("Success! Logging in...\n");
					if (noTest) {
						view view = new view(users.get(username), username);
					}
					return true;
				}
				else {
					// If user name found, but password incorrect, return false
					return false;
				}
			}
		}
		
		return false;
	}
}
