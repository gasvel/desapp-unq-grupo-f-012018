package model;

import java.util.Date;

public class Reservation {
	
	private long timeOfRent;
	private Date startDate;
	private Date endDate;
	private User client;
	
	HandlerReserRent serviceRR;
	
	public Reservation() {
		
	}
	
	public Reservation(Date start,Date end, User userClient) {
		this.client = userClient;
		this.timeOfRent = end.getTime() - start.getTime() ;
		this.startDate = start;
		this.endDate = end;
		this.serviceRR = new HandlerReserRent();
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public void confirmReservation(Post post) {
		serviceRR.confirmReservation(post, this.startDate, this.endDate, this.timeOfRent, this.client, this);
	}
}
