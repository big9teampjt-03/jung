package petcare.model;

import lombok.Getter;

@Getter
public enum Role {
	MEMBER("ROLE_MEMBER"), 
	DOCTOR("ROLE_DOCTOR"),
	ADMIN("ROLE_ADMIN");
	
    String role;
    
    Role(String role) {
    	this.role = role;
    }
    public String value() {
    	return role;
    }
}
