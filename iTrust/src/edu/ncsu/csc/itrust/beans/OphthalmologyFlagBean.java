package edu.ncsu.csc.itrust.beans;

import edu.ncsu.csc.itrust.enums.OphthalmologyFlag;

/**
 * Represents an Ophthalmology Flag.
 * @author Pruett
 *
 */
public class OphthalmologyFlagBean {

	private long fid;
	private long mid;
	private OphthalmologyFlag value;
	private boolean flagged;
	
	/** All persons age 60 or over have a higher risk for glaucoma */
	public static final int glaucomaAge = 60;
	
	/** African Americans age 40 or over have a higher risk for glaucoma */
	public static final int aaGlaucomaAge = 40;
	
	/**
	 * returns the FID
	 * @return
	 */
	public long getFid() {
		return fid;
	}
	
	/**
	 * sets the FID
	 * @param fid
	 */
	public void setFid(long fid) {
		this.fid = fid;
	}
	
	/**
	 * gets the MID
	 * @return
	 */
	public long getMid() {
		return mid;
	}
	
	/**
	 * sets the MID
	 * @param mid
	 */
	public void setMid(long mid) {
		this.mid = mid;
	}
	
	/**
	 * gets the flag enum value
	 * @return
	 */
	public OphthalmologyFlag getValue() {
		return value;
	}
	
	/**
	 * sets the flag enum value
	 * @param value
	 */
	public void setValue(OphthalmologyFlag value) {
		this.value = value;
	}
	
	/**
	 * returns true if the flag is set
	 * @return
	 */
	public boolean isFlagged() {
		return flagged;
	}
	
	/**
	 * sets the flag
	 * @param flagged
	 */
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}
}
