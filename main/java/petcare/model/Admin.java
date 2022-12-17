package petcare.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Admin {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long adminID;
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
