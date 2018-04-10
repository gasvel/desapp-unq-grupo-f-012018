package model;

import java.util.Date;

public class Reservation {
	
	private long timeOfRent;
	private Date startDate;
	private Date endDate;
	private User client;
	
//	private HandlerReserRent serviceRR;
	
	public Reservation() {
		
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Reservation(Date start,Date end, User userClient) {
		this.client = userClient;
		this.timeOfRent = end.getTime() - start.getTime() ;
		this.startDate = start;
		this.endDate = end;
//		this.serviceRR = new HandlerReserRent();
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public long getTimeOfRent() {
		return timeOfRent;
	}

	public void setTimeOfRent(long timeOfRent) {
		this.timeOfRent = timeOfRent;
	}
	
//	public void confirmReservation(Post post) {
//		serviceRR.confirmReservation(post, this.startDate, this.endDate, this.timeOfRent, this.client, this);
//	}
	
	
}
