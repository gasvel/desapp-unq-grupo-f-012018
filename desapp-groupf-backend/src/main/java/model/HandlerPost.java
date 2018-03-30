package model;

public class HandlerPost {
	
	public void newPost(TypeVehicle typeVehicle, Integer capacity, String location, String description,
		String phoneNumber, String availability, String photo,
		Integer priceH, Integer priceD, String addressToDrop, String addressToPickUp, User owner) {
		
		if(typeVehicle == null) {
			throwError();
		}
		
		if(ArgumentsValidator.isNullOrEmptyString(location, description, phoneNumber, 
				availability, photo, addressToDrop, addressToPickUp)) {
			throwError();
		}
		
		if(ArgumentsValidator.isNegativeInt(capacity, priceD, priceH)) {
			throwError();
		}
		
		Post newPost = new Post(typeVehicle, capacity, location, description,
				phoneNumber, availability, photo,
				priceH, priceD, addressToDrop, addressToPickUp); 
		
		owner.addPost(newPost);
	}
	
	public void throwError() {
		throw new IllegalArgumentException("Invalid argument");
	}
}
