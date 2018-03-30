package model;

public class Score {
	private String description;
	private Integer value;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
