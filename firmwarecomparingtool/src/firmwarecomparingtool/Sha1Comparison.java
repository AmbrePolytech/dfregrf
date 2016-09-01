package firmwarecomparingtool;

/**
 * The Sha1Comparison class performs the SHA1 comparison of two Firmwares
 * @author Ambre Person
 *
 */

public class Sha1Comparison extends Comparison {

	/**
	 * The Sha1Comparison constructor creates a new Sha1Comparison using two Firmwares, it is a Comparison child
	 * @param b : base Firmware
	 * @param s : suspect Firmware
	 * The msg is modified to specify that the Comparison is a Sha1Comparison
	 */
	
	public Sha1Comparison(Firmware b, Firmware s) {
		super(b, s);
		this.msg = "SHA1 "+ this.msg;
	}

	/**
	 * The compare method compares the SHA1 hash of the base and the suspect Firmwares and updates the msg with the result of the comparison and the different MD5 hash codes
	 * It also updates the score value and the boolean value
	 */
	
	@Override
	public void compare() {
		if (this.base.sha1.equalsIgnoreCase(this.suspect.sha1)){
			this.setValue(true);
			this.setMsg("There is no SHA1 hash modification.\n\n");
			this.score = 0;
		} else {
			this.setValue(false);
			this.setMsg("There is a SHA1 hash modification.\nBase : " + this.base.sha1 +"\nSuspect : " + this.suspect.sha1 + "\n\n");
			this.score = 1;
		}
	}

}
