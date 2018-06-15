package service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.Rent;
import model.Reservation;


public class RentService extends GenericService<Rent> {

	private static final long serialVersionUID = 5091408438519087152L;

	@Override
	@Transactional
	public void save(Rent rent){
		ArgumentsValidator.validateRent(rent);
		super.save(rent);
	}
	
	@Override
	@Transactional
	public void update(Rent rent){
		ArgumentsValidator.validateRent(rent);
		super.update(rent);
	}
	
	@Override
	@Transactional
	public void delete(Rent rent){
		super.delete(rent);
	}
	
	@Override
	@Transactional
	public Rent getById(Integer id){
		return super.getById(id);
	}
	
	@Override
	@Transactional
	public List<Rent> retriveAll(){
		return super.retriveAll();
	}
	
	public Float calculateCost(long timeOfRent, Integer priceDay, Integer priceHour){
		long days = TimeUnit.DAYS.convert(timeOfRent, TimeUnit.MILLISECONDS);
        long hours  = TimeUnit.HOURS.convert(timeOfRent, TimeUnit.MILLISECONDS) - (days * 24);
        float costDays = days * priceDay;
        float costHours = hours * priceHour;
		return costDays + costHours;
	}

	@Transactional
	public void newRent(Reservation reser) {
		Rent newRent = new Rent(this.calculateCost(reser.getTimeOfRent(), reser.getPost().getPriceDay(), reser.getPost().getPriceHour()),
								reser.getStartDate(), reser.getEndDate(),reser.getClient());
		newRent.setPost(reser.getPost());
		super.save(newRent);
	}
	
}
