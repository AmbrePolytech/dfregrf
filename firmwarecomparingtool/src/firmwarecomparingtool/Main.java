package firmwarecomparingtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Main class which launch the 3 parts of the Model View Controller pattern used in the application
 * @author Ambre Person
 *
 */

public class Main {

	public static void main(String[] args) {

		Model m = new Model();
		View v = new View();
		Controller c = new Controller(m,v);
	

		
	}	
}
