package firmwarecomparingtool;

public class Comparison {
	protected Firmware base;
	protected Firmware suspect;
	protected Boolean value;
	protected String msg;
	protected int score;
	
	public Comparison(Firmware b, Firmware s){
		this.base = b;
		this.suspect = s;
		this.msg = "Comparison between the base : " + this.base.path + " and the suspect : " + this.suspect.path +".\n";
		this.score = 0;
	}
	
	public void compare() {
		
	}

	public Firmware getBase() {
		return base;
	}

	public void setBase(Firmware base) {
		this.base = base;
	}

	public Firmware getSuspect() {
		return suspect;
	}

	public void setSuspect(Firmware suspect) {
		this.suspect = suspect;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = this.msg + msg;
	}
}
