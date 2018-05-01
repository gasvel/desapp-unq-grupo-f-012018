package service;

import model.ArgumentsValidator;
import model.User;

public class UserService extends GenericService<User> {
	
	private static final long serialVersionUID = 2131359482422367092L;
	
	@Override
	public void save(User user){
		ArgumentsValidator.validateUser(user);
		super.save(user);
	}
	
	@Override
	public void update(User user){
		ArgumentsValidator.validateUser(user);
		super.update(user);
	}

}
