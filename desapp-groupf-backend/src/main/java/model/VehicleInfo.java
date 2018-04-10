package model;

public class VehicleInfo {
	private TypeVehicle typeVehicle;
	private Integer capacity; 
	private String description;
	private String photo;

	public VehicleInfo(TypeVehicle typeVehicle,	Integer capacity, String description, String photo) {
		this.typeVehicle = typeVehicle;
		this.capacity = capacity;
		this.description = description;
		this.photo = photo;
	}
	
	public TypeVehicle getTypeVehicle() {
		return typeVehicle;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public String getDescription() {
		return description;
	}

	public String getPhoto() {
		return photo;
	}
}
