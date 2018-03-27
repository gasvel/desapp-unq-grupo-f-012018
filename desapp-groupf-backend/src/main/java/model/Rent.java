package model;

import java.util.Date;

public class Rent {

	Float cost;
	Date startDate;
	Date endDate;
	User client;
	
	public Rent(Float cost, Date startDate, Date endDate, User client) {
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.client = client;
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
}
