package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.Post;
import model.Reservation;
import persistence.ReserRepository;

public class ReserService extends GenericService<Reservation> {

	private static final long serialVersionUID = -2541042982395208583L;

	@Override
	@Transactional
	public void save(Reservation reserv){
		super.save(reserv);
	}
	
	@Transactional
	public void saveWithPost(Reservation reserv, Post post){
		ArgumentsValidator.validateReserv(reserv, post);
		post.addNewReservation(reserv);
		super.save(reserv);
	}
	
	@Override
	@Transactional
	public void update(Reservation reserv){
		super.update(reserv);
	}
	
	@Override
	@Transactional
	public void delete(Reservation reserv){
		super.delete(reserv);
	}
	
	@Override
	@Transactional
	public Reservation getById(Integer id){
		return super.getById(id);
	}
	
	@Override
	@Transactional
	public List<Reservation> retriveAll(){
		return super.retriveAll();
	}

	@Transactional
	public List<Reservation> getAllFromCreator(String emailUser) {
		ReserRepository repo = (ReserRepository) this.getRepository();
		return repo.getAllFromCreator(emailUser);
		
	}
}
