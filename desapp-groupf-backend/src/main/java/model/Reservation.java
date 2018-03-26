package model;

public class Reservation {
	
	private String timeOfRent;
	private User client;
	
	public Reservation() {
		
	}
	
	public Reservation(String time, User userClient) {
		this.client = userClient;
		this.timeOfRent = time;
		
	}

	public String getTimeOfRent() {
		return timeOfRent;
	}

	public void setTimeOfRent(String timeOfRent) {
		this.timeOfRent = timeOfRent;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	
	

}
