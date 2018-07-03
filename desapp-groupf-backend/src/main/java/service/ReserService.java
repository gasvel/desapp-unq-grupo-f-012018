package service;

import java.util.List;

import javax.ws.rs.ClientErrorException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.Post;
import model.Reservation;
import persistence.ReserRepository;

public class ReserService extends GenericService<Reservation> {
	
	public static Logger log = Logger.getLogger(ReserService.class);

	private static final long serialVersionUID = -2541042982395208583L;
	private boolean testMode = false;
	private EmailService emailServ;
	private RentService rentServ;
	
	@Override
	@Transactional
	public void save(Reservation reserv) throws ClientErrorException{
		super.save(reserv);
	}
	
	@Transactional
	public void saveWithPost(Reservation reserv, Post post) throws Exception{
		this.checkDate(reserv,post);
		this.rentServ.checkDate(reserv,post);
		ArgumentsValidator.validateReserv(reserv, post);
		super.save(reserv);
		if(!this.testMode) {
			try {
				this.emailServ.newReservation(post.getCreator(),reserv.getClient());
			}
			catch(Exception e) {
				log.error("Error al enviar email",e);
			}
		}
	}
	
	@Transactional
	public void checkDate(Reservation reserv, Post post){
		ReserRepository repo = (ReserRepository) this.getRepository();
		repo.checkDate(reserv.getStartDate(),reserv.getEndDate(),post.getId());
	}
	
	@Override
	@Transactional
	public void update(Reservation reserv) throws ClientErrorException{
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
	
	public void setTestMode(boolean b) {
		this.testMode = b;
		
	}
	
	public boolean getTestMode(){
		return this.testMode;
	}
	
	public EmailService getEmailServ() {
		return emailServ;
	}

	public void setEmailServ(EmailService emailServ) {
		this.emailServ = emailServ;
	}

	public RentService getRentServ() {
		return rentServ;
	}

	public void setRentServ(RentService rentServ) {
		this.rentServ = rentServ;
	}
	
	
}
