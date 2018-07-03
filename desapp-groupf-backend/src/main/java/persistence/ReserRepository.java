package persistence;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import exception.InvalidModelException;
import model.Reservation;

public class ReserRepository extends HibernateGenericDAO<Reservation> implements GenericRepository<Reservation> {

	private static final long serialVersionUID = 3139340845361476560L;
	public static Logger log = Logger.getLogger(ReserRepository.class);

	@Override
	protected Class<Reservation> getDomainClass() {
		return Reservation.class;
	}

	public List<Reservation> getAllFromCreator(String emailUser) {
		return this.findAll().stream()
		.filter(res -> res.getPost().getCreator().getEmail().equals(emailUser))
		.collect(Collectors.toList());

	}
	
	public void checkDate(Date startDate, Date endDate,Integer idPost){
		DetachedCriteria criteria = DetachedCriteria.forClass(Reservation.class);
		criteria.createAlias("post", "p").add(Restrictions.and(
				Restrictions.eq("p.id", idPost),
				Restrictions.or(
						(Restrictions.and(Restrictions.le("startDate", startDate),Restrictions.ge("endDate", startDate))),
						(Restrictions.and(Restrictions.le("startDate", endDate),Restrictions.ge("endDate", endDate))))
						)
				
				);
		@SuppressWarnings("unchecked")
		List<Reservation> founded = (List<Reservation>) this.getHibernateTemplate().findByCriteria(criteria);
		if(!founded.isEmpty()){
			throw new InvalidModelException("A reservation already exist in that period");
		}
	}

}
