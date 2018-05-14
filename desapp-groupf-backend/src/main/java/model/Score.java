package model;

public class Score extends Entity{

	private static final long serialVersionUID = -2303641468955834123L;
	private String description;
	private Integer value;
	
	
	public Score() {
		
	}
	
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
