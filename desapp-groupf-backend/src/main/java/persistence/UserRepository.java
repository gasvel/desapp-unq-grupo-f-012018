package persistence;

import org.apache.log4j.Logger;

import model.User;

public class UserRepository extends HibernateGenericDAO<User> implements GenericRepository<User> {

	private static final long serialVersionUID = 4036535812105672110L;
	public static Logger log = Logger.getLogger(UserRepository.class);
	
	@Override
	protected Class<User> getDomainClass() {
		return User.class;
	}
	
}
