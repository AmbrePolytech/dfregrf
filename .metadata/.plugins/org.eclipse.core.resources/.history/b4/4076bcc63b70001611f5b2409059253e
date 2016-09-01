package firmwarecomparingtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Firmware {
	protected String vendor;
	protected String model;
	protected String md5;
	protected String sha1;
	protected String path;
	protected String version;
	protected int length;

	
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
	
	public Firmware (String path) throws IOException {
		super();
		this.vendor = "";
		this.model = "";
		this.version = "";
		this.path = path;
		
		try {
			File f = new File(path);
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			String md5Sum = getFileChecksum(md5Digest, f);
			this.md5 = md5Sum;
			
			MessageDigest sha1Digest = MessageDigest.getInstance("SHA");
			String sha1Sum = getFileChecksum(sha1Digest, f);
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
	
	@SuppressWarnings("unused")
	private static String getFileChecksum(MessageDigest digest, File file) throws IOException
	{
	    //Get file input stream for reading the file content
	    FileInputStream fis = new FileInputStream(file);
	     
	    //Create byte array to read data in chunks
	    byte[] byteArray = new byte[1024];
	    int bytesCount = 0; 
	      
	    //Read file data and update in message digest
	    while ((bytesCount = fis.read(byteArray)) != -1) {
	        digest.update(byteArray, 0, bytesCount);
	    };
	     
	    //close the stream; We don't need it now.
	    fis.close();
	     
	    //Get the hash's bytes
	    byte[] bytes = digest.digest();
	     
	    //This bytes[] has bytes in decimal format;
	    //Convert it to hexadecimal format
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	     
	    //return complete hash
	   return sb.toString();
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
