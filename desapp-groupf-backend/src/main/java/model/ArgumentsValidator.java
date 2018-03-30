package model;

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

		return (!(control == c[10]));
	}
	
	public static boolean isInvalidFullName(String name) {
		if(isNullOrEmptyString(name) || (name.length() < 4 || name.length() > 50)) 
			return true;
		return false;
	}
}
