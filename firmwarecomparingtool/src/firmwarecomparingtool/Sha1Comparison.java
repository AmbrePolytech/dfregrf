package firmwarecomparingtool;

public class Sha1Comparison extends Comparison {

	public Sha1Comparison(Firmware b, Firmware s) {
		super(b, s);
		this.msg = "SHA1 "+ this.msg;
	}

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
