package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public final class HandlerReserRent {
	
	public static Logger log = Logger.getLogger(HandlerReserRent.class);

	public void newReservation(Date start, Date end, User client, Post post) {
		if(ArgumentsValidator.areInvalidDates(start, end)){
			throwError();
		}
		Reservation newReservation = new Reservation(start, end, client);
		post.addNewReservation(newReservation);
	}

	public void throwError() {
		throw new IllegalArgumentException("Invalid argument or there is another reservation for those days");
	}
	
	private Float calculateCost(long timeOfRent, Integer priceDay, Integer priceHour) {
		long days = TimeUnit.DAYS.convert(timeOfRent, TimeUnit.MILLISECONDS);
        long hours  = TimeUnit.HOURS.convert(timeOfRent, TimeUnit.MILLISECONDS) - (days * 24);
        float costDays = days * priceDay;
        float costHours = hours * priceHour;
		return costDays + costHours;
	}

	public void confirmReservation(Post post, User client,
			Reservation reservation) {		
		Float cost = this.calculateCost(reservation.getTimeOfRent(), post.getPriceDay(), post.getPriceHour());
		Rent newRent = new Rent(cost, reservation.getStartDate(), reservation.getEndDate(), client);
		post.addNewRent(newRent);
		post.removeReservation(reservation);		
	}
	
	public static void confirmVehiclePickUp(Rent rent,Post post,User user) {
		switch(rent.getState()) {
		case New:
			log.info("RENT STATE " + post.getCreator().getCuil().equals(user.getCuil()));
			if(user.getCuil().equals(rent.getClient().getCuil())) {
				rent.clientConfirmsPickUp(rent.PICK_UP_TIME);
				
			}
			else if(post.getCreator().getCuil().equals(user.getCuil())) {
				rent.ownerConfirmsPickUp(rent.PICK_UP_TIME);
			}
			break;
			
		case ClientConfirmedPickUp:
			if(!user.getCuil().equals(rent.getClient().getCuil())) {
				rent.pickUpConfirmed();
			}
			break;
			
		case OwnerConfirmedPickUp:
			if(user.getCuil().equals(rent.getClient().getCuil())) {
				rent.pickUpConfirmed();
			}
			break;

		default:
			break;
			
		}		
	}
	
	public static void confirmVehicleReturns(Rent rent, Post post, User user, User otherUser, Score score){
		
		switch(rent.getState()) {
		case PickUpConfirmed:
			if(user.getCuil().equals(rent.getClient().getCuil())){
				rent.clientConfirmsReturn();
				otherUser.addScore(score);
			} else if(post.getCreator().getCuil().equals(user.getCuil())) {
				rent.ownerConfirmsReturn();
				otherUser.addScore(score);
			}
			break;
		
		case ClientConfirmedReturn:
			if(!user.getCuil().equals(rent.getClient().getCuil())) {
				rent.transactionDone();
			}
			break;
			
		case OwnerConfirmedReturn:
			if(user.getCuil().equals(rent.getClient().getCuil())) {
				rent.transactionDone();
			}
			break;

		default:
			break;
		}
	}
}
