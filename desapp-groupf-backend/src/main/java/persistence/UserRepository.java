package persistence;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import model.User;

public class UserRepository extends HibernateGenericDAO<User> implements GenericRepository<User> {

	private static final long serialVersionUID = 4036535812105672110L;
	public static Logger log = Logger.getLogger(UserRepository.class);
	
	@Override
	protected Class<User> getDomainClass() {
		return User.class;
	}
	
	
	public User getByEmail(String mail){
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
	    criteria.add(Restrictions.eq("email", mail));
		return (User) this.getHibernateTemplate().findByCriteria(criteria).get(0);
	}
	
	public Boolean checkByEmail(String mail){
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
	    criteria.add(Restrictions.eq("email", mail));
		return !this.getHibernateTemplate().findByCriteria(criteria).isEmpty();
	}
}
