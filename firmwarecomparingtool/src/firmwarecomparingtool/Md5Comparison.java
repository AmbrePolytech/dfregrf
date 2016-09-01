package firmwarecomparingtool;

/**
 * The Md5Comparison class performs the MD5 comparison of two Firmwares
 * @author Ambre Person
 *
 */

public class Md5Comparison extends Comparison {
	
	/**
	 * The Md5Comparison constructor creates a new Md5Comparison using two Firmwares, it is a Comparison child
	 * @param b : base Firmware
	 * @param s : suspect Firmware
	 * The msg is modified to specify that the Comparison is a Md5Comparison
	 */
	
	public Md5Comparison(Firmware b, Firmware s) {
		super(b, s);
		this.msg = "MD5 "+ this.msg;
	}

	/**
	 * The compare method compares the MD5 hash of the base and the suspect Firmwares and updates the msg with the result of the comparison and the different MD5 hash codes
	 * It also updates the score value and the boolean value
	 */
	
	@Override
	public void compare() {
		if (this.base.md5.equalsIgnoreCase(this.suspect.md5)){
			this.setValue(true);
			this.setMsg("There is no MD5 hash modification.\n\n");
			this.score = 0;
		} else {
			this.setValue(false);
			this.setMsg("There is a MD5 hash modification.\nBase : " + this.base.md5 +"\nSuspect : " + this.suspect.md5 + "\n\n");
			this.score = 1;
		}
	}

}
