package model;

public class Score {
	String description;
	Integer value;
	
	public Score(String description) {
		this.description = description;
	}
	
	public Score(String description, Integer value) {
		this.description = description;
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;		
	}
}
