package petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import petcare.model.CommentCounsel;

public interface CommentCounselRepository extends JpaRepository<CommentCounsel, Long> {

	
	
	  //JPQ:가장 단순이 sql문 처리, 엔터티 객체를 중심으로 사용
	 // @Query("select sc from comment_counsel sc where bcounselnum=?1")//EAGER
		  @Query("select sc from comment_counsel sc join fetch sc.bCounsel where bcounselnum=?1")//LAZY(패치조인 사용) 
		  public List<CommentCounsel> findByNum(Long bcounselnum);
		  
		  //댓글추가
		  @Modifying//이거 넣어야 insert  
		  @Query(
		  value="insert into comment_counsel(content,regdate,bcounselnum) values(?1,now(),?2)"
		  , nativeQuery=true)//nativeQuery로 sql문 작성 가능 
		  public void insert(String content, Long counselID);
		 
	 
	
}
