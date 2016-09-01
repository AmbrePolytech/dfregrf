package firmwarecomparingtool;

import java.util.*;

public class Model extends Observable {
	
	protected Database db;
	protected Firmware suspect;
	protected List<Firmware> baseline;
	protected String log;
	protected List<Comparison> comparisons;
	
	public Model(){
		this.db = new Database("jdbc:mysql://localhost/db_firmware","root","mysql");
		this.baseline = this.db.createBaseline();
		this.log = "";
		this.suspect = new Firmware();
	}

	public void setComparisons(List<Comparison> comparisons) {
		this.comparisons = comparisons;
	}

	public void setSuspect(Firmware suspect) {
		this.suspect = suspect;
	}

	public void setLog(String log) {
		this.log = this.log+log;
		setChanged();
		notifyObservers();
	}

	public void setBaseline(List<Firmware> baseline) {
		this.baseline = baseline;
	}
	
	
}
