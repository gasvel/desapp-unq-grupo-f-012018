package service;

import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.User;
import persistence.UserRepository;

public class UserService extends GenericService<User> {
	
	private static final long serialVersionUID = 2131359482422367092L;
	
	@Override
	@Transactional
	public void save(User user){
		ArgumentsValidator.validateUser(user);
		super.save(user);
	}
	
	@Override
	@Transactional
	public void update(User user){
		ArgumentsValidator.validateUser(user);
		super.update(user);
	}

	@Transactional
	public User getByEmail(String email) {
		UserRepository userRep = (UserRepository) this.getRepository();
		return userRep.getByEmail(email);
    }
}
