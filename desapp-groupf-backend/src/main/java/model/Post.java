package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
	
	private TypeVehicle typeVehicle;
	private Integer capacity;
	private String location;
	private String description;
	private String phoneNumber;
	private List<Reservation> reservations;
	private List<Rent> rents;
	private String availability;
	private String photo;
	private Integer priceDay;
	private Integer priceHour;
	private String addressToDrop;
	private String addressToPickUp;	

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
	}

	public Post() {
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
	
	public void addNewRent(Rent newRent) {
		this.rents.add(newRent);
	}

	public void removeReservation(Reservation reservation) {
		this.reservations.remove(reservation);
	}

	public TypeVehicle getTypeVehicle() {
		return typeVehicle;
	}

	public void setTypeVehicle(TypeVehicle typeVehicle) {
		this.typeVehicle = typeVehicle;
	}

	public void addNewReservation(Reservation newReservation) {
		this.reservations.add(newReservation);		
	}
	
}
