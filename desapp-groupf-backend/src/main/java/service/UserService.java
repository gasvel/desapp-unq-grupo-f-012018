package service;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import model.ArgumentsValidator;
import model.User;
import persistence.UserRepository;

public class UserService extends GenericService<User> {
	
	public static Logger log = Logger.getLogger(UserService.class);

	
	private EmailService emailServ;
	private static final long serialVersionUID = 2131359482422367092L;
	
	@Override
	@Transactional
	public void save(User user){
		ArgumentsValidator.validateUser(user);
		try {
			emailServ.welcomeMsg(user);
		} catch (MessagingException e) {
			log.error("Error al enviar mail", e);
		}
		super.save(user);
	}
	
	@Override
	@Transactional
	public void update(User user){
		ArgumentsValidator.validateUser(user);
		super.update(user);
	}

	@Transactional
	public User getByEmail(String email) {
		UserRepository userRep = (UserRepository) this.getRepository();
		return userRep.getByEmail(email);
    }

	@Transactional
	public void addCredits(Integer id, float credits) {
		User user = this.getById(id);
		user.addCredits(credits);
		this.update(user);
		
	}
	
	@Transactional
	public void removeCredits(Integer id, float credits) {
		User user = this.getById(id);
		user.removeCredits(credits);
		this.update(user);
		
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
	
	
}
