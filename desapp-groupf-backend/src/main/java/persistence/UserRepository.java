package persistence;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import model.User;

public class UserRepository extends HibernateGenericDAO<User> implements GenericRepository<User> {

	private static final long serialVersionUID = 4036535812105672110L;
	public static Logger log = Logger.getLogger(UserRepository.class);
	
	@Override
	protected Class<User> getDomainClass() {
		return User.class;
	}
	
	

}
