package firmwarecomparingtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * The Crypto class manages the encrypting and decrypting of files
 * It is a wrapper to use these functionalities easily
 * @author Ambre Person
 *
 */

public class Crypto {
	
	/**
	 * Field description :
	 * ALGO : constant String which represent the algorithm used to perform the cryptography, AES by default
	 */
	
	private static final String ALGO = "AES";
	
	/**
	 * The encrypt method calls the crypto method with the cipher set to do encryption
	 * @param key : 16 bytes String key in order to correspond to the AES specification, this could change if the used algorithm is modified
	 * @param input : input File to be encrypted
	 * @param output : encrypted File
	 */
	
	public static void encrypt(String key, File input, File output){
		crypto(Cipher.ENCRYPT_MODE, key, input, output);
	}
	
	/**
	 * The decrypt method calls the crypto method with the cipher set to do decryption
	 * @param key : 16 bytes String key in order to correspond to the AES specification, this could change if the used algorithm is modified
	 * @param input : input File to be decrypted
	 * @param output : decrypted File
	 */
	
	public static void decrypt(String key, File input, File output){
		crypto(Cipher.DECRYPT_MODE, key, input, output);
	}
	
	/**
	 * The crypto method performs the cryptography work depending on the mode given in parameter
	 * @param mode : Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE to respectively encrypt or decrypt
	 * @param key : 16 bytes String key in order to correspond to the AES specification, this could change if the used algorithm is modified
	 * @param input : input File to be encrypted or decrypted
	 * @param output : encrypted or decrypted File
	 */
	
	public static void crypto(int mode, String key, File input, File output){
		
		try {
			Key secretKey = new SecretKeySpec(key.getBytes(), ALGO);
			Cipher cipher = Cipher.getInstance(ALGO);
			cipher.init(mode, secretKey);
			FileInputStream iStream = new FileInputStream(input);
			byte[] iBytes = new byte[(int) input.length()];
			iStream.read(iBytes);
			
			byte[] oBytes = cipher.doFinal(iBytes);
			
			FileOutputStream oStream = new FileOutputStream(output);
			oStream.write(oBytes);
			
			iStream.close();
			oStream.close();
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
