package firmwarecomparingtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The Firmware class represents all the caracteristics of a Firmware in the baseline Database
 * @author Ambre Person
 *
 */

public class Firmware {
	
	/**
	 * Field description :
	 * vendor : String representing the vendor name
	 * model : String representing the PLC model
	 * md5 : String representing the MD5 hash code
	 * sha1 : String representing the SHA1 hash code
	 * path : String representing the path of the image on the disk
	 * version : String representing the version of the Firmware
	 * length : int representing the length of the Firmware image 
	 */
	
	protected String vendor;
	protected String model;
	protected String md5;
	protected String sha1;
	protected String path;
	protected String version;
	protected int length;

	/**
	 * The default Firmware constructor initializes all the fields to "" or 0
	 */
	
	public Firmware(){
		super();
		this.vendor = "";
		this.model = "";
		this.md5 = "";
		this.sha1 = "";
		this.path = "";
		this.version = "";
		this.length = 0;
	}
	
	/**
	 * The Firmware constructor creates a new Firmware based on the different characteristics of a Firmware
	 * @param ven : String of the vendor name
	 * @param m : String of the PLC model name
	 * @param md5 : String of the MD5 hash code
	 * @param sha1 : String of the SHA1 hash code
	 * @param p : String of the path of the Firmware image on the disk
	 * @param ver : String of the version of the Firmware
	 * @param l : int of the length of the Firmware image
	 * This constructor is used to create the baseline because all the characteristics are available on the Database
	 */
	
	public Firmware(String ven, String m, String md5, String sha1, String p, String ver, int l){
		super();
		this.vendor = ven;
		this.model = m;
		this.md5 = md5;
		this.sha1 = sha1;
		this.path = p;
		this.version = ver;
		this.length = l;

	}
	
	/**
	 * The Firmware constructor creates a Firmware object using only its path
	 * @param path : String of the path of the Firmware image on the disk
	 * @throws IOException : throws IOException in case of bad reading of the Firmware image
	 * This constructor set the vendor, model and version to "" and computes the MD5, SHA1 and length fields
	 * This constructor is used to create the suspect Firmware object just using its path
	 */
	
	public Firmware (String path) throws IOException {
		super();
		this.vendor = "";
		this.model = "";
		this.version = "";
		this.path = path;
		
		try {
			File f = new File(path);
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			String md5Sum = Checksum.getFileChecksum(md5Digest, f);
			this.md5 = md5Sum;
			
			MessageDigest sha1Digest = MessageDigest.getInstance("SHA");
			String sha1Sum = Checksum.getFileChecksum(sha1Digest, f);
			this.sha1 = sha1Sum;
			
			this.length = (int)f.length();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		} 
		

	}

	@Override
	public String toString() {
		return "Firmware [vendor=" + vendor + ", model=" + model + ", md5="
				+ md5 + ", sha1=" + sha1 + ", path=" + path + ", version="
				+ version + ", length=" + length + "]";
	}
	
	

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
}
