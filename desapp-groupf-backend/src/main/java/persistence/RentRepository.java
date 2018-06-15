package persistence;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import model.Rent;

public class RentRepository extends HibernateGenericDAO<Rent> implements GenericRepository<Rent> {

	private static final long serialVersionUID = -1463415754947442801L;
	public static Logger log = Logger.getLogger(RentRepository.class);

	@Override
	protected Class<Rent> getDomainClass() {
		return Rent.class;
	}
	
	public Float calculateCost(long timeOfRent, Integer priceDay, Integer priceHour){
		long days = TimeUnit.DAYS.convert(timeOfRent, TimeUnit.MILLISECONDS);
        long hours  = TimeUnit.HOURS.convert(timeOfRent, TimeUnit.MILLISECONDS) - (days * 24);
        float costDays = days * priceDay;
        float costHours = hours * priceHour;
		return costDays + costHours;
	}

}
