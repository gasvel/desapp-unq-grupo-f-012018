package persistence;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import exception.InvalidModelException;
import model.Rent;
import model.Rent_State;

public class RentRepository extends HibernateGenericDAO<Rent> implements GenericRepository<Rent> {

	private static final long serialVersionUID = -1463415754947442801L;
	public static Logger log = Logger.getLogger(RentRepository.class);

	@Override
	protected Class<Rent> getDomainClass() {
		return Rent.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Rent> allToConfirmOwner(String mail) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Rent.class);
		criteria.createAlias("post", "p").createAlias("p.creator", "user").add(Restrictions.and(Restrictions.not(Restrictions.eq("state", Rent_State.Cancelled))
				,Restrictions.not(Restrictions.eq("state", Rent_State.RentDone)), Restrictions.not(Restrictions.eq("state", Rent_State.OwnerConfirmedReturn))
				,Restrictions.not(Restrictions.eq("state", Rent_State.Cancelled)), Restrictions.not(Restrictions.eq("state", Rent_State.OwnerConfirmedPickUp))
				,Restrictions.eq("user.email", mail)));
		return (List<Rent>) this.getHibernateTemplate().findByCriteria(criteria);

	}

	@SuppressWarnings("unchecked")
	public List<Rent> allRentsDoneOwner(String mail) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Rent.class);
		criteria.createAlias("client", "user").add(Restrictions.or(Restrictions.eq("state", Rent_State.Cancelled)
				,Restrictions.eq("state", Rent_State.RentDone))).add(Restrictions.eq("user.email", mail));
		return (List<Rent>) this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Rent> allRentsDoneClient(String mail) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Rent.class);
		criteria.createAlias("post", "p").createAlias("p.creator", "user").add(
				Restrictions.or(Restrictions.eq("state", Rent_State.Cancelled),
				Restrictions.eq("state", Rent_State.RentDone))).add(
				Restrictions.eq("user.email", mail));
		return (List<Rent>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<Rent> allToConfirmClient(String mail) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Rent.class);
		criteria.createAlias("client", "user").add(Restrictions.and(Restrictions.not(Restrictions.eq("state", Rent_State.Cancelled))
				,Restrictions.not(Restrictions.eq("state", Rent_State.RentDone)),Restrictions.not(Restrictions.eq("state", Rent_State.ClientConfirmedReturn))
				,Restrictions.not(Restrictions.eq("state", Rent_State.ClientConfirmedPickUp)),Restrictions.not(Restrictions.eq("state", Rent_State.Cancelled))
				,Restrictions.eq("user.email", mail)));
		return (List<Rent>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	public void checkDate(Date startDate, Date endDate,Integer idPost) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Rent.class);
		criteria.createAlias("post", "p").add(Restrictions.and(
				Restrictions.eq("p.id", idPost),
				Restrictions.or(
						(Restrictions.and(Restrictions.le("startDate", startDate),Restrictions.ge("endDate", startDate))),
						(Restrictions.and(Restrictions.le("startDate", endDate),Restrictions.ge("endDate", endDate))))
						)
				
				);
		@SuppressWarnings("unchecked")
		List<Rent> founded = (List<Rent>) this.getHibernateTemplate().findByCriteria(criteria);
		if(!founded.isEmpty()){
			throw new InvalidModelException("There is another rent in that period");
		}
	}
}
