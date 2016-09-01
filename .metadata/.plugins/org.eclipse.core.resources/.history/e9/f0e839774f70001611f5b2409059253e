package firmwarecomparingtool;

public class LengthComparison extends Comparison {

	public LengthComparison(Firmware b, Firmware s) {
		super(b, s);
		this.msg = "Length "+ this.msg;
	}

	@Override
	public void compare() {
		if(this.base.length != this.suspect.length){
			this.setValue(false);
			if (this.base.length < this.suspect.length){
				this.setMsg("The modification is an addition\n\n");
			} else {
				this.setMsg("The modification is a deletion\n\n");
			}
		} else {
			this.setValue(true);
			this.setMsg("There is no length modification\n\n");
		}
		this.score = Math.abs(this.base.length - this.suspect.length);
	}

	
}
