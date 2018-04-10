package model;

public class VehicleInfo {
	TypeVehicle typeVehicle;
	Integer capacity; 
	String description;
	String photo;
	
	public VehicleInfo(TypeVehicle typeVehicle,	Integer capacity, String description, String photo) {
		this.typeVehicle = typeVehicle;
		this.capacity = capacity;
		this.description = description;
		this.photo = photo;
	}
}
