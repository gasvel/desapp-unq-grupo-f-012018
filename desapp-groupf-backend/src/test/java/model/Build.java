package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Build {
	//User builder
	public static class UserBuilder {
		private User anUser = new User();		
		
		public UserBuilder withScores(List<Score> scores) {
			anUser.setScore(scores);
			return this;
		}
		
		public UserBuilder withCuil(String cuil) {
			anUser.setCuil(cuil);
			return this;
		}
		

		
		public UserBuilder valid(){
			anUser.setCuil("20108181843");
			anUser.addCredits(10000000);
			anUser.setAddress("Calle falsa 123");
			anUser.setName("Carlos");
			anUser.setEmail("carlito@gmail.com");
			
			return this;
		}
		
		public User build() {
			return anUser;
		}
	}
	
	public static UserBuilder anUser() {
		return new UserBuilder();
	}
	
	//Score builder
	public static class ScoreBuilder {
		 private Score aScore = new Score("Description test");
		
		public ScoreBuilder withValue(Integer value) {
			aScore.setValue(value);
			return this;
		}
		
		public Score build() {
			return aScore;
		}
	}
	
	public static ScoreBuilder aScore() {
		return new ScoreBuilder();
	}
	
	//Rent builder
	public static class RentBuilder{
		private Rent aRent = new Rent();
		
		public RentBuilder buildValidRent() {
			Date startDate = new GregorianCalendar(2019, Calendar.MARCH, 29).getTime();
			Date endDate = new GregorianCalendar(2019, Calendar.JUNE, 29).getTime();
			aRent.setStartDate(startDate);
			aRent.setEndDate(endDate);
			aRent.setState(Rent_State.New);
			aRent.setCost(100f);
			return this;
		}
		
		public RentBuilder withUser(User user) {
			aRent.setClient(user);
			return this;
		}
		
		public Rent build() {			
			return aRent;
		}
	}
	
	public static RentBuilder aRent() {
		return new RentBuilder();
	}
	
	//Rent builder
	public static class ReservationBuilder{
		private Reservation aReser = new Reservation();
		
		public Reservation build(){
			return aReser;
		}
		
		public ReservationBuilder buildValidReservation(){
			Date startDate = new GregorianCalendar(2019, Calendar.MARCH, 29).getTime();
			Date endDate = new GregorianCalendar(2019, Calendar.JUNE, 29).getTime();
			aReser.setStartDate(startDate);
			aReser.setEndDate(endDate);
			aReser.generateTimeRent();
			
			return this;
		}
		
	}
	
	public static ReservationBuilder aReservation(){
		return new ReservationBuilder();
	}
	
	//Rent builder
	public static class PostBuilder{
		private Post aPost = new Post();
		
		public Post build() {
			return aPost;
		}
		
		public PostBuilder buildValidPost() {
			aPost.setTypeVehicle(TypeVehicle.Car);
			aPost.setAddressToDrop("Calle falsa 123");
			aPost.setAddressToPickUp("Calle falsa 123");
			aPost.setPhoneNumber("45689359");
			aPost.setCapacity(2);
			aPost.setAvailability("disponible");
			aPost.setLocation("Mi casa :v");
			aPost.setPhoto("autito.jpg");
			aPost.setPriceDay(20);
			aPost.setPriceHour(2);
			aPost.setDescription("descripcion alv");
			
			return this;
		}
		
	}
	
	public static PostBuilder aPost() {
		return new PostBuilder();
	}
	
}


