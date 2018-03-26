package model;

import java.util.ArrayList;
import java.util.List;

public class Post {
	
	Vehicle vehicle;
	TypeVehicle typeVehicle;
	Integer capacity;
	String location;
	String description;
	Integer phoneNumber;
	List<Reservation> reservations;
	List<Rent> rents;
	String availability;
	String photo;
	Integer price;
	String addressToDrop;
	String addressToPickUp;
	

	public Post(Vehicle vehicle, TypeVehicle typeVehicle, Integer capacity, String location, String description,
			Integer phoneNumber, List<Reservation> reservations, List<Rent> rents, String availability, String photo,
			Integer price, String addressToDrop, String addressToPickUp) {
		
		this.vehicle = vehicle;
		this.typeVehicle = typeVehicle;
		this.capacity = capacity;
		this.location = location;
		this.description = description;
		this.phoneNumber = phoneNumber;
		this.reservations = reservations;
		this.rents = rents;
		this.availability = availability;
		this.photo = photo;
		this.price = price;
		this.addressToDrop = addressToDrop;
		this.addressToPickUp = addressToPickUp;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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
	
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(Integer phoneNumber) {
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
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
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
	
}
