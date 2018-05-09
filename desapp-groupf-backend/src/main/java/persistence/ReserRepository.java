package persistence;

import org.apache.log4j.Logger;

import model.Reservation;

public class ReserRepository extends HibernateGenericDAO<Reservation> implements GenericRepository<Reservation> {

	private static final long serialVersionUID = 3139340845361476560L;
	public static Logger log = Logger.getLogger(ReserRepository.class);

	@Override
	protected Class<Reservation> getDomainClass() {
		return Reservation.class;
	}

}
