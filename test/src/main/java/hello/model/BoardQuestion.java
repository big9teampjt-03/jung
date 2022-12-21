package hello.model;

import java.util.Date;
import java.util.List;

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
@Entity(name="board_question")
public class BoardQuestion {
		@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long questionID;
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
		
		@OneToMany(mappedBy = "bQuestion",cascade=CascadeType.REMOVE)
		@JsonIgnoreProperties("bQuestion")
		public List<CommentQuestion> qlist;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="wuser")
		@JsonIgnore
		private User user;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="doctorID")
		private Doctor doctor;
		
		@Transient
		private MultipartFile uploadquestion;
		private String questionimage;
}
