package model;

public class HandlerUser {
	
	public User newUser(User user) {
		
		ArgumentsValidator.validateUser(user);
		
		return user;
	}
	

	
	public void rateUser(User user, Integer score, String description) {
		if(!ArgumentsValidator.isValidScore(score)){
			ArgumentsValidator.throwError();
		}
		user.addScore(new Score(description, score));
		checkBan(user);
	}
	
	public void checkBan(User user) {
		if(user.scoreAvg() < 4 && user.getScore().size() >= 10) {
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
