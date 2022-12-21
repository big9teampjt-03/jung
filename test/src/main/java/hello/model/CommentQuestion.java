package hello.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity(name="comment_question")
public class CommentQuestion {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long comquestionID;
	private String username;
	private String content;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="regdate")
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date regdate;
	//private int grp;
	//private int grps;
	//private int grpl;
	
	@ManyToOne
	@JoinColumn(name="bquestionnum")
	@JsonIgnore
	private BoardQuestion bQuestion;
	
	@ManyToOne
	@JoinColumn(name="wuser")
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JoinColumn(name="wdoctor")
	private Doctor doctor;
}
