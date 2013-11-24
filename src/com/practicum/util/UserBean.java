package com.practicum.util;

public class UserBean {
	
	protected int rut;
    protected String username; // primer nombre  o nombre empresa
    protected String password;
    protected String email;
    protected int type;
    protected boolean valid;
    protected boolean legit;
	
	
    public int getRut() {
        return rut;
 	}

     public void setRut(int newRut) {
        rut = newRut;
 	}
    
    public String getPassword() {
       return password;
	}

    public void setPassword(String newPassword) {
       password = newPassword;
	}
	
    public String getUsername() {
       return username;
			}

    public void setUsername(String newUsername) {
       username = newUsername;
			}
		
    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isValid() {
       return valid;
	}

    public void setValid(boolean newValid) {
       valid = newValid;
	}
	public boolean isLegit() {
	       return legit;
		}

	    public void setLegit(boolean newLegit) {
	       legit= newLegit;
		}
  
    public String getEmail() {
        return email;
 	}

    public void setEmail(String newEmail) {
        email = newEmail;
 	}
    
    public void parseUserBean(UserBean user){
    	rut=user.getRut();
    	username=user.getUsername();
    	password=user.getPassword();
    	email=user.getEmail();
    	type=user.getType();
    	valid=user.isValid();
    	legit=user.isLegit();
    }
    
}