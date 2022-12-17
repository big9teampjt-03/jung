package petcare.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Member {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long memberID;
	
	@Column(nullable=false)
	private String username;
	private String password;
	private String nickname;
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy="member")
	private List<Pet> pets;
	
}
