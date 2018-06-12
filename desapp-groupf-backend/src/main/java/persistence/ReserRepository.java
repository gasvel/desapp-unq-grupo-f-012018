package persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

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

}
