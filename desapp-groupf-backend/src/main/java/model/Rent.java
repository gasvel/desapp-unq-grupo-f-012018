package model;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Rent {

	private Float cost;
	private Date startDate;
	private Date endDate;
	private User client;
	private Rent_State state;
	private Timer timer = new Timer();
	
	public Rent(Float cost, Date startDate, Date endDate, User client) {
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.client = client;
		this.state = Rent_State.New;
	}

	public Float getCost() {
		return cost;
	}
	
	public void setCost(Float cost) {
		this.cost = cost;
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
	
	public User getClient() {
		return client;
	}
	
	public void setClient(User client) {
		this.client = client;
	}

	public Rent_State getState() {
		return state;
	}

	public void setState(Rent_State state) {
		this.state = state;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void clientConfirmsPickUp() {
		this.setState(Rent_State.ClientConfirmedPickUp);
		this.timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				state = Rent_State.Cancelled;
				
			}
		}, TimeUnit.MINUTES.toMillis(30));
		
	}

	public void ownerConfirmsPickUp() {
		this.setState(Rent_State.OwnerConfirmedPickUp);
		this.timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				state = Rent_State.PickUpConfirmed;
				
			}
		}, TimeUnit.MINUTES.toMillis(30));
		
	}

	public void pickUpConfirmed() {
		timer.cancel();
		timer.purge();
		this.setState(Rent_State.PickUpConfirmed);
		
	}
	
	
}
