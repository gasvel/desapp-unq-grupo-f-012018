package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HandlerReserRent {

	public void newReservation(Date start, Date end, User client, Post post) {
		if(ArgumentsValidator.areInvalidDates(start, end) || post.isThereAnotherReservation(start,end) || post.isThereAnotherRent(start, end)){
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
	
	public void confirmVehiclePickUp(Rent rent,Post post,User user) {
		switch(rent.getState()) {
		case New:
			if(user.getCuil() == rent.getClient().getCuil()) {
				rent.clientConfirmsPickUp(rent.PICK_UP_TIME);
				
			}
			else if(user.getPosts().contains(post)) {
				rent.ownerConfirmsPickUp(rent.PICK_UP_TIME);
			}
			break;
			
		case ClientConfirmedPickUp:
			if(user.getCuil() != rent.getClient().getCuil()) {
				rent.pickUpConfirmed();
			}
			break;
			
		case OwnerConfirmedPickUp:
			if(user.getCuil() == rent.getClient().getCuil()) {
				rent.pickUpConfirmed();
			}
			break;

		default:
			break;
			
		}		
	}
	
	public void confirmVehicleReturns(Rent rent, Post post, User user, User otherUser, Score score){
		
		switch(rent.getState()) {
		case PickUpConfirmed:
			if(user.getCuil() == rent.getClient().getCuil()){
				rent.clientConfirmsReturn();
				otherUser.addScore(score);
			} else if(user.getPosts().contains(post)) {
				rent.ownerConfirmsReturn();
				otherUser.addScore(score);
			}
			break;
		
		case ClientConfirmedReturn:
			if(user.getCuil() != rent.getClient().getCuil()) {
				rent.transactionDone();
			}
			break;
			
		case OwnerConfirmedReturn:
			if(user.getCuil() == rent.getClient().getCuil()) {
				rent.transactionDone();
			}
			break;

		default:
			break;
		}
	}
}