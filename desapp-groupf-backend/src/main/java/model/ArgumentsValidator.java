package model;

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
}
