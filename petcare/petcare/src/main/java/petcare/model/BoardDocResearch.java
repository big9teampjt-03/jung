package petcare.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class BoardDocResearch {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long docresearchID;
	private String map;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="memberID")
	private Doctor doctor;
}
