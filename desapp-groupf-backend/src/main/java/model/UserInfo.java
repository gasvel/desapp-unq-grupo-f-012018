package model;

public class UserInfo {
	String location;
	String phoneNumber;
	String addressToDrop;
	String addressToPickUp;
	
	public UserInfo(String location, String phoneNumber, String addressToDrop, String addressToPickUp) {		
		this.addressToDrop = addressToDrop;
		this.addressToPickUp = addressToPickUp;
		this.location = location;
		this.phoneNumber = phoneNumber;
	}

}
