package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	
	private long timeOfRent;
	private Date startDate;
	private Date endDate;
	private User client;
	public long days;
	public long hours;
	
	public Reservation() {
		
	}
	
	public Reservation(Date start,Date end, User userClient) {
		this.client = userClient;
		this.timeOfRent = end.getTime() - start.getTime() ;
		this.startDate = start;
		this.endDate = end;
		
        
        this.days = TimeUnit.DAYS.convert(this.timeOfRent, TimeUnit.MILLISECONDS);
        this.hours  = TimeUnit.HOURS.convert(timeOfRent, TimeUnit.MILLISECONDS) - (this.days * 24);
	}



	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public void confirmReservation(Post post) {
		
		Rent newRent;
	}
	
	
	

}
