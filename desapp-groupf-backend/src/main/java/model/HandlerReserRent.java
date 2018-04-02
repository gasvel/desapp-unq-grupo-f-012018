package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HandlerReserRent {

	public Reservation newReservation(Date start, Date end, User client) {
		Reservation newReservation = new Reservation(start, end, client);
		return newReservation;
	}

	private Float calculateCost(long timeOfRent, Integer priceDay, Integer priceHour) {
		long days = TimeUnit.DAYS.convert(timeOfRent, TimeUnit.MILLISECONDS);
        long hours  = TimeUnit.HOURS.convert(timeOfRent, TimeUnit.MILLISECONDS) - (days * 24);
        float costDays = days * priceDay;
        float costHours = hours * priceHour;
		return costDays + costHours;
	}

	public void confirmReservation(Post post, Date startDate, Date endDate, long timeOfRent, User client,
			Reservation reservation) {
		
		Float cost = this.calculateCost(timeOfRent, post.getPriceDay(), post.getPriceHour());
		Rent newRent = new Rent(cost, startDate, endDate, client);
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
}
