package service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.User;

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

	@SuppressWarnings("deprecation")
	public User getByEmail(String email) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		Criterion criterion = Restrictions.eq("email", email);
		return (User) criteria.add(criterion).uniqueResult();
    }
}
