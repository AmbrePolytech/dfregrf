package firmwarecomparingtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
/**
 * The Checksum class performs the computation of a hash code depending on the digest given
 * @author Ambre Person
 *
 */
public class Checksum {
	
	/**
	 * The getFileChecksum computes the hash code respecting to a specific algorithm.
	 * @param digest : Digest representing the wished algorithm
	 * @param file : File which hash code will be compute
	 * @return : String containing the hash code 
	 * @throws IOException : throws IOException in case of reading error
	 * This method is used to compute the MD5 and SHA1 hash codes of the suspect Firmware
	 */
	
	public static String getFileChecksum(MessageDigest digest, File file) throws IOException
	{
	    FileInputStream fis = new FileInputStream(file);
	     
	    byte[] byteArray = new byte[1024];
	    int bytesCount = 0; 
	      
	    while ((bytesCount = fis.read(byteArray)) != -1) {
	        digest.update(byteArray, 0, bytesCount);
	    };
	     
	    fis.close();
	     
	    byte[] bytes = digest.digest();
	     
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	     
	    return sb.toString();
	}
}
