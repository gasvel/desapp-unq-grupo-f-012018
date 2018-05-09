package persistence;

import org.apache.log4j.Logger;

import model.Rent;

public class RentRepository extends HibernateGenericDAO<Rent> implements GenericRepository<Rent> {

	private static final long serialVersionUID = -1463415754947442801L;
	public static Logger log = Logger.getLogger(RentRepository.class);

	@Override
	protected Class<Rent> getDomainClass() {
		return Rent.class;
	}

}
