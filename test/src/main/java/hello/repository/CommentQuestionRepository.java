package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hello.model.CommentQuestion;


public interface CommentQuestionRepository extends JpaRepository<CommentQuestion, Long> {
	
		  @Query("select sc from comment_question sc join fetch sc.bQuestion where bquestionnum=?1")//LAZY(패치조인 사용) 
		  public List<CommentQuestion> findByNum(Long bquestionnum);
		  
		 
		  @Modifying
		  @Query(
		  value="insert into comment_question(content,regdate,bquestionnum,username,wuser) values(?1,now(),?2,?3,?4)"
		  , nativeQuery=true) 
		  public void insert(String content,Long bquestionnum, String username,Long wuser);

		 
	 
	
}
