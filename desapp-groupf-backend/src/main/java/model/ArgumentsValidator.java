package model;

import java.util.Date;

import org.apache.commons.validator.routines.EmailValidator;

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
	
	public static void validateRent(Rent rent){
		if(ArgumentsValidator.areInvalidDates(rent.getStartDate(), rent.getEndDate())){
			throwError();
		}
	}
	
	public static void validateReserv(Reservation reserv, Post post){
		if(ArgumentsValidator.areInvalidDates(reserv.getStartDate(), reserv.getEndDate()) || post.isThereAnotherReservation(reserv.getStartDate(),reserv.getEndDate()) || post.isThereAnotherRent(reserv.getStartDate(), reserv.getEndDate())){
			throwError();
		}
	}

	public static void validateUser(User user) {

		if(ArgumentsValidator.isInvalidCUIT(user.getCuil())){throwError();};
		if( ArgumentsValidator.isNotAValidMailAddress(user.getEmail())){throwError();};
		if(ArgumentsValidator.isNullOrEmptyString(user.getAddress())){throwError();};
		if(ArgumentsValidator.isInvalidFullName(user.getName())) {
			throwError();
		}
		
	}
	
	public static void validatePost(Post post) {
		if(post.getTypeVehicle() == null) {
			throwError();
		}
		
		if(ArgumentsValidator.isNullOrEmptyString(post.getAddressToDrop(),post.getAddressToPickUp(),post.getAvailability(),
				post.getDescription(),post.getLocation(),post.getPhoneNumber(),post.getPhoto())) {
			throwError();
		}
		
		if(ArgumentsValidator.isNegativeInt(post.getCapacity(), post.getPriceDay(), post.getPriceHour())) {
			throwError();
		}

		
	}
	
	public static void throwError() {
		throw new IllegalArgumentException("Invalid argument");
	}

}
