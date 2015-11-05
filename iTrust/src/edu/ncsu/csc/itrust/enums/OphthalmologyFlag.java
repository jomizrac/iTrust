package edu.ncsu.csc.itrust.enums;

/**
 * OphthalmologyFlag is an enum that represents the different flags that exist
 * in iTrust for ophthalmology.
 * @author Pruett
 * 
 */
public enum OphthalmologyFlag {
	
	/** Cataracts Risks*/
		/** Current or former smoker */
		Smoker("Smoker", "smoker"),
		/** has Diabetes */
		Diabetes("Diabetes", "diabetes"),
		
	/** Age-related macular degeneration Risks*/
		/** Caucasian */
		RaceCaucasian("Race Caucasian","caucasian"),
		/** Family history */
		FamilyHistoryAMD("Family History of Age-related Macular Degeneration", "AMD"),
		
	/** Glaucoma Risks */
		/** African American and over age 40*/
		AfricanAmerican40("Race African American and over age 40", "AA40"),
		/** Over age 60 */
		Over60("Over age 60", "60"),
		/** Family history */
		FamilyHistoryGlaucoma("Family History of Glaucoma","Glaucoma");

	private String name; // represents a good output string
	private String id; // represents a good id/name for an HTML form

	/**
	 * Constructor for a FlagValue that takes a name and id.
	 * 
	 * @param name
	 *            the flag name
	 * @param id
	 *            the flag ID
	 */
	private OphthalmologyFlag(String name, String id) {
		this.name = name;
		this.id = id;
	}

	/**
	 * Custom replacement method for the un-overridable valueOf method. Parses
	 * non-case sensitive and can use either ID or Name as needed.
	 * 
	 * @param idOrName
	 *            A string, assumed to likely be a match to either ID or name of
	 *            some OphthalmologyFlag
	 * @return The corresponding OphthalmologyFlag
	 */
	public static OphthalmologyFlag parseEnum(String idOrName) {
		for (OphthalmologyFlag v : values())
			if (v.id.equalsIgnoreCase(idOrName)
					|| v.name.equalsIgnoreCase(idOrName))
				return v;
		throw new IllegalArgumentException();
	}

	/**
	 * Returns the ID of the flag.
	 * 
	 * @return the ID of the flag
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Returns the string value of the flag.
	 * 
	 * @return the string value of the flag
	 */
	@Override
	public String toString() {
		if (name == null)
			return "";
		return name;
	}
}
