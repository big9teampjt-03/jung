package hello.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="user")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;

	@Column(nullable = false)
	private String username;
	private String password;
	private String nickname;
	private String tel;
	
	@Column(name="regdate")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date regdate;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties("user")
	private List<Pet> pets;
	
	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties("user")
	private List<Doctor> doctor;
	
	@OneToMany(mappedBy="user")
	private List<BoardCounsel> list;
    }
