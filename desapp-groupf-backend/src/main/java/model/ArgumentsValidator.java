package model;

import java.util.Date;

import org.apache.commons.validator.routines.EmailValidator;

import exception.InvalidModelException;

public class ArgumentsValidator {
	public static boolean isNullOrEmptyString(String... strArr) {
	    for (String st : strArr) {
	        if  (st==null || st.isEmpty())
	           return true;
	    } 
	    return false;
	}
	
	public static boolean isNegativeInt(Integer... intArr) {
	    for (Integer i : intArr) {
	        if  (i==null || i < 0)
	           return true;
	    } 
	    return false;
	}
	
	public static boolean isValidScore(Integer score) {
		return (score >= 1 && score <= 5);
	    
	}
	
	public static boolean isNotAValidMailAddress(String... strArr) {
	    if(isNullOrEmptyString(strArr))
	    	return true;
	    
		EmailValidator mailValid = EmailValidator.getInstance();
		
		for (String st : strArr) {
			
	        if  (!mailValid.isValid(st))
	           return true;
	    } 
	    return false;
	}
	
	public static boolean isInvalidCUIT(String cuit) {
		if(cuit.length() != 11)
			return true;

		int factor = 5;

		int[] c = new int[11];
		int result = 0;

		for (int i = 0; i <10; i++) {
			c[i] = Integer.valueOf(Character.toString(cuit.charAt(i)));
			result = result + c[i] * factor;
			factor = (factor == 2)?7:factor - 1;
		}

		c[10] = Integer.valueOf(Character.toString(cuit.charAt(10)));

		int control = (11 - (result % 11)) % 11;

		return ((control != c[10]));
	}
	
	public static boolean isInvalidFullName(String name) {
		return (isNullOrEmptyString(name) || (name.length() < 4 || name.length() > 50));

	}
	
	public static boolean areInvalidDates(Date start, Date end){
		return (start.after(end) || start.before(new Date()));
	}
	
	public static void validateRent(Rent rent) throws InvalidModelException{
		if(ArgumentsValidator.areInvalidDates(rent.getStartDate(), rent.getEndDate())){
			throwErrorModel("Invalid dates of rent, check fields");
		}
	}
	
	public static void validateReserv(Reservation reserv, Post post) throws InvalidModelException{
		if(ArgumentsValidator.areInvalidDates(reserv.getStartDate(), reserv.getEndDate())){
			throwErrorModel("Invalid dates of reservation, check fields");
		}
		if(post.isThereAnotherReservation(reserv.getStartDate(),reserv.getEndDate())){
			throwErrorModel("Invalid reservation, it already exits a reservation on these days");
		}
		if(post.isThereAnotherRent(reserv.getStartDate(), reserv.getEndDate())){
			throwErrorModel("Invalid reservation, it already exits a rent on these days");
		}
	}

	public static void validateUser(User user) throws InvalidModelException {

		if(ArgumentsValidator.isInvalidCUIT(user.getCuil())){throwErrorModel("Invalid CUIT");;}
		if( ArgumentsValidator.isNotAValidMailAddress(user.getEmail())){throwErrorModel("Invalid mail");;}
		if(ArgumentsValidator.isNullOrEmptyString(user.getAddress())){throwErrorModel("Invalid address");;}
		if(ArgumentsValidator.isInvalidFullName(user.getName())) {
			throwErrorModel("Invalid name");
		}
		
	}
	
	public static void validatePost(Post post) throws InvalidModelException {
		if(post.getTypeVehicle() == null) {
			throwErrorModel("Invalid vehicle type");
		}
		
		//agregar photo location
		if(ArgumentsValidator.isNullOrEmptyString(post.getAddressToDrop(),post.getAddressToPickUp(),post.getAvailability(),
				post.getDescription(),post.getPhoneNumber())) { 
			throwErrorModel("Invalid post, check fields address availability description and phonenumber");
		}
		
		if(ArgumentsValidator.isNegativeInt(post.getCapacity(), post.getPriceDay(), post.getPriceHour())) {
			throwErrorModel("Invalid post, check fields capacity and prices");
		}

		
	}
	
	public static void throwError() {
		throw new IllegalArgumentException("Invalid argument");
	}
	
	
	public static void throwErrorModel(String message) throws InvalidModelException {
		throw new InvalidModelException(message);
	}

	public static void validateCost(Float cost, Float credits)  throws InvalidModelException{
		if(cost > credits) {
			throwErrorModel("Insufficient credit");
		}		
	}

}
