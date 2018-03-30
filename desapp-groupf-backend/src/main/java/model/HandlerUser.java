package model;

public class HandlerUser {
	
	public User newUser(String cuil, String address, String name,String mail) {
		if(ArgumentsValidator.isInvalidCUIT(cuil) || ArgumentsValidator.isNotAValidMailAddress(mail) ||
				ArgumentsValidator.isNullOrEmptyString(address) || ArgumentsValidator.isInvalidFullName(name)) {
			throwError();
		}
		
		User newUser = new User(cuil,address,name,mail);
		return newUser;

	}
	
	public void throwError() {
		throw new IllegalArgumentException("Invalid argument");
	}

}
