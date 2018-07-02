package service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.HandlerReserRent;
import model.Post;
import model.Rent;
import model.Reservation;
import model.Score;
import model.User;
import persistence.RentRepository;


public class RentService extends GenericService<Rent> {
	
	public static Logger log = Logger.getLogger(RentService.class);
	private UserService userService;

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

	@Transactional
	public void confirmPickUp(Rent rent, User user) {
		HandlerReserRent.confirmVehiclePickUp(rent, rent.getPost(),user);
		log.info("RENTA CAMBIADA " + rent.getState());
		this.update(rent);
	}
	
	@Transactional
	public User confirmVehicleReturns(Rent rent, User user, Integer score) {
		userService = new UserService();
		Post thePost = rent.getPost();
		User otherUser;
		if(user.getCuil().equals(rent.getClient().getCuil())){
			otherUser = thePost.getCreator();
		} else {
			otherUser = rent.getClient();
		}
		
		HandlerReserRent.confirmVehicleReturns(rent, thePost,user, otherUser, new Score("", score));
		log.info("RENTA CAMBIADA " + rent.getState());
		this.update(rent);
		return otherUser;
	}

	@Transactional
	public List<Rent> allToConfirmOwner(String mail) {
		RentRepository repo = (RentRepository) this.getRepository();
		return repo.allToConfirmOwner(mail);
	}

	@Transactional
	public List<Rent> allToConfirmClient(String mail) {
		RentRepository repo = (RentRepository) this.getRepository();
		return repo.allToConfirmClient(mail);
	}
	
	public void setUserService(final UserService userServ){
		userService = userServ;
	}
	
}
