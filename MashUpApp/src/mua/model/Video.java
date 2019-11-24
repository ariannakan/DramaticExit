package mua.model;

public class Video {
	public final String id;
	public final String charName;
	public final String sentence;
	public boolean system;      // when TRUE this is actually stored in S3 bucket
	
	public Video (String id, String charName, String sentence) {
		this.id = id;
		this.charName = charName;
		this.sentence = sentence;
	}
	
	public Video (String id, String charName, String sentence, boolean system) {
		this.id = id;
		this.charName = charName;
		this.sentence = sentence;
		this.system = system;
	}
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean s) { system = s; }
	
	/**
	 * Equality of Constants determined by name alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Video) {
			Video other = (Video) o;
			return id.equals(other.id);
		}
		
		return false;  // not a Constant
	}
}
