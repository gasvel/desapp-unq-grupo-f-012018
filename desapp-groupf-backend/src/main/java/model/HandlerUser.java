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
	
	public void rateUser(User user, Integer score, String description) {
		if(!ArgumentsValidator.isValidScore(score)){
			throwError();
		}
		user.addScore(new Score(description, score));
		checkBan(user);
	}
	
	public void checkBan(User user) {
		if(user.getScore() < 4 && user.getScores().size() >= 10) {
			banUser(user);
		}
	}
	
	public void banUser(User user) {
		switch(user.getState()) {
			case REGULAR:
				user.setState(User_State.BANNED);
				break;
			case REFORMED:
				user.setState(User_State.PERMABANNED);
				break;
			case BANNED:
				break;
			case PERMABANNED:
				break;
			default:
				break;
		}
	}

}
