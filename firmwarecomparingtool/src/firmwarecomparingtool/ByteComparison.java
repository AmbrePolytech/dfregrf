package firmwarecomparingtool;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * The ByteComparison class performs the Byte comparison of two Firmwares
 * @author Ambre Person
 *
 */

public class ByteComparison extends Comparison {
	
	/**
	 * The ByteComparison constructor creates a new ByteComparison using two Firmwares, it is a Comparison child
	 * @param b : base Firmware
	 * @param s : suspect Firmware
	 * The msg is modified to specify that the Comparison is a ByteComparison
	 */
	
	public ByteComparison(Firmware b, Firmware s) {
		super(b, s);
		this.msg = "Byte "+ this.msg;
	}

	/**
	 * The compare method compares the bytes of the base and the suspect Firmwares one by one and updates the msg with the result of the comparison (matching percentage)
	 * It also updates the score value and the boolean value
	 */
	
	@Override
	public void compare() {
		FileInputStream fB;
		FileInputStream fS;
		int bB;
		int bS;
		int similar = 0;
		int total = 0;
		int first = 0;
		boolean bFirst = true; 
		try {
			fB = new FileInputStream(this.base.path);
			fS = new FileInputStream(this.suspect.path);
			
			while (fB.available()>0 && fS.available()>0){
				bB = fB.read();
				bS = fS.read();
				
				if (bFirst && bB != bS){
					first = total;
					bFirst = false;
				}
				
				if (bB == bS){
					similar++;
				}
				total++;
			}
			this.score = total-similar;
			fB.close();
			fS.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (total == 0){
				this.setValue(false);
				this.setMsg("There is a problem with file reading.\n\n");
			} else if (similar == total){
				this.setValue(true);
				this.setMsg("There is no byte modification, the two firmware images are the same at 100%.\n\n");
			} else {
				this.setValue(false);
				this.setMsg("There are "+ this.score +" byte modifications, the two firmware images are the same at "+ ((float)similar/(float)total)*100 +"%.\n\nThe first modification is on the byte number " + first+".\n\n");
			}
		}
		 
	}

}
