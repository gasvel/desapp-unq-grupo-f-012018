package model;

public class UserInfo {
	private String location;
	private String phoneNumber;
	private String addressToDrop;
	private String addressToPickUp;
	
	public UserInfo(String location, String phoneNumber, String addressToDrop, String addressToPickUp) {		
		this.addressToDrop = addressToDrop;
		this.addressToPickUp = addressToPickUp;
		this.location = location;
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddressToDrop() {
		return addressToDrop;
	}

	public String getAddressToPickUp() {
		return addressToPickUp;
	}

}
