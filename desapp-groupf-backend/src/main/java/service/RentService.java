package service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.Rent;
import model.Reservation;
import persistence.RentRepository;


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

	@Transactional
	public void newRent(Reservation reser) {
		RentRepository repo = (RentRepository) this.getRepository();
		Rent newRent = new Rent(repo.calculateCost(reser.getTimeOfRent(), reser.getPost().getPriceDay(), reser.getPost().getPriceHour()),
								reser.getStartDate(), reser.getEndDate(),reser.getClient());
		newRent.setPost(reser.getPost());
		reser.getPost().addNewRent(newRent);
		super.save(newRent);
	}
	
}
