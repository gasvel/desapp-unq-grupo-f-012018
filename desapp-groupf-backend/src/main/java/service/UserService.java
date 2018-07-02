package service;

import javax.mail.MessagingException;
import javax.ws.rs.ClientErrorException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import exception.InvalidModelException;
import model.ArgumentsValidator;
import model.User;
import persistence.UserRepository;

public class UserService extends GenericService<User> {
	
	public static Logger log = Logger.getLogger(UserService.class);

	private boolean testMode = false;
	private EmailService emailServ;
	private static final long serialVersionUID = 2131359482422367092L;
	
	@Override
	@Transactional
	public void save(User user) throws ClientErrorException{
		ArgumentsValidator.validateUser(user);
		if(!this.testMode){
			try {
				emailServ.welcomeMsg(user);
			} catch (MessagingException e) {
				log.error("Error al enviar mail", e);
			}
		}

		super.save(user);
	}
	
	@Override
	@Transactional
	public void update(User user) throws ClientErrorException{
		ArgumentsValidator.validateUser(user);
		super.update(user);
	}

	@Transactional
	public User getByEmail(String email) {
		UserRepository userRep = (UserRepository) this.getRepository();
		return userRep.getByEmail(email);
    }

	@Transactional
	public void addCredits(Integer id, float credits) throws Exception {
		User user = this.getById(id);
		user.addCredits(credits);
		this.update(user);
		if(!this.testMode){
			try {
				emailServ.creditsAdded(credits,user);
			} catch (MessagingException e) {
				log.error("Error al enviar mail", e);
			}
		}
		
	}
	
	@Transactional
	public void removeCredits(Integer id, float credits) throws Exception {
		User user = this.getById(id);
		user.removeCredits(credits);
		this.update(user);
		if(!this.testMode){
			try {
				emailServ.creditsExtracted(credits,user);
			} catch (MessagingException e) {
				log.error("Error al enviar mail", e);
			}
		}
		
	}

	@Transactional
	public Boolean checkMail(String mail) {
		UserRepository userRepo = (UserRepository) this.getRepository();
		return userRepo.checkByEmail(mail);
	}

	public EmailService getEmailServ() {
		return emailServ;
	}

	public void setEmailServ(EmailService emailServ) {
		this.emailServ = emailServ;
	}

	public void setTestMode(boolean b) {
		this.testMode = b;
		
	}
	
	public boolean getTestMode(){
		return this.testMode;
	}
}
