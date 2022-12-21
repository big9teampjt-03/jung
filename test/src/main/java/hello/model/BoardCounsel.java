package hello.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity(name="board_counsel")
public class BoardCounsel {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long counselID;
	private String title;
	private String content;
	@Column(nullable=false)
	private String username;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date regdate;
	private Long hitcount;
	private Long replycnt;
	@PrePersist
	public void prePersist() {
		this.hitcount = this.hitcount==null?0:this.hitcount;
		this.replycnt = this.replycnt==null?0:this.replycnt;
	}
	private Long likes;
	private boolean secret;
	
	@OneToMany(mappedBy = "bCounsel",cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties("bCounsel")
	public List<CommentCounsel> clist;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="wuser")
	@JsonIgnore
	private User user;
	
	@Transient
	private MultipartFile uploadcounsel;
	private String counselimage;

	
}
