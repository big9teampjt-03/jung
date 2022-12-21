package hello.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorid;
	private String docname;
	private String doclicensenum;
	private String dochospital;
	private String dochospitaltel;
	private String dochospitaladdr;
	private String dochospitalpostal;
	
	@Column(name="regdate")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date regdate;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Transient
	private MultipartFile uploaddocprofile;
	private String imagedocprofile;

	@Transient
	private MultipartFile uploaddoclicense;
	private String imagedoclicense;


	@Column(name = "approval", nullable = false, columnDefinition = "int default 0")
	private int approval;
}
