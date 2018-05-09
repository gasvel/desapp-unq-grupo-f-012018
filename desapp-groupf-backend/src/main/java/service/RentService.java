package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.Rent;

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
	public Rent getById(long id){
		return super.getById(id);
	}
	
	@Override
	@Transactional
	public List<Rent> retriveAll(){
		return super.retriveAll();
	}
}
