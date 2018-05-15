package model;

public class Credential {

	public String email;
	public String password;
	
	public Credential(){
		
	}
	
	public Credential(String mail, String pass){
		this.email = mail;
		this.password = pass;
	}
}
