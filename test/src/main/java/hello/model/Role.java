package hello.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	USER("ROLE_USER"), DOCTOR("ROLE_DOCTOR"), ADMIN("ROLE_ADMIN");

//	public static class Roles {
//		public static final String USER = "ROLE_USER";
//		public static final String DOCTOR = "ROLE_DOCTOR";
//		public static final String ADMIN = "ROLE_ADMIN";
//	}
	
	private String role;

	private Role(String role) {
		this.role = role;
	}

//	public String value() {
//    	return role;
//    }

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}
}
