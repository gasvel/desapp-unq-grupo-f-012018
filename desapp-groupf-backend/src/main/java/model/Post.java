package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
	
	TypeVehicle typeVehicle;
	Integer capacity;
	String location;
	String description;
	String phoneNumber;
	List<Reservation> reservations;
	List<Rent> rents;
	String availability;
	String photo;
	Integer priceDay;
	Integer priceHour;
	String addressToDrop;
	String addressToPickUp;
	
	HandlerReserRent serviceRR; 
	

	public Post( TypeVehicle typeVehicle, Integer capacity, String location, String description,
			String phoneNumber, String availability, String photo,
			Integer priceH,Integer priceD, String addressToDrop, String addressToPickUp) {
		
		this.typeVehicle = typeVehicle;
		this.capacity = capacity;
		this.location = location;
		this.description = description;
		this.phoneNumber = phoneNumber;
		this.reservations = new ArrayList<Reservation>();
		this.rents = new ArrayList<Rent>();
		this.availability = availability;
		this.photo = photo;
		this.priceDay = priceD;
		this.priceHour = priceH;
		this.addressToDrop = addressToDrop;
		this.addressToPickUp = addressToPickUp;
		this.serviceRR = new HandlerReserRent();
	}

	public Integer getCapacity() {
		return capacity;
	}
	
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public List<Rent> getRents() {
		return rents;
	}
	
	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}
	
	public String getAvailability() {
		return availability;
	}
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Integer getPriceDay() {
		return priceDay;
	}
	
	public void setPriceDay(Integer price) {
		this.priceDay = price;
	}
	
	
	
	public Integer getPriceHour() {
		return priceHour;
	}

	public void setPriceHour(Integer priceHour) {
		this.priceHour = priceHour;
	}

	public String getAddressToDrop() {
		return addressToDrop;
	}
	
	
	public void setAddressToDrop(String addressToDrop) {
		this.addressToDrop = addressToDrop;
	}
	
	public String getAddressToPickUp() {
		return addressToPickUp;
	}
	
	public void setAddressToPickUp(String addressToPickUp) {
		this.addressToPickUp = addressToPickUp;
	}
	
	public void newReservation(User client,Date start,Date end) {
		Reservation newReservation = serviceRR.newReservation(start,end,client);
		this.reservations.add(newReservation);
	}

	public void addNewRent(Rent newRent) {
		this.rents.add(newRent);
	}

	public void removeReservation(Reservation reservation) {
		this.reservations.remove(reservation);
	}
	
}
