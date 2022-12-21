package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hello.model.CommentCounsel;

public interface CommentCounselRepository extends JpaRepository<CommentCounsel, Long> {

		  @Query("select sc from comment_counsel sc join fetch sc.bCounsel where bcounselnum=?1")//LAZY(패치조인 사용) 
		  public List<CommentCounsel> findByNum(Long bcounselnum);
		  
		  //댓글추가
		  @Modifying 
		  @Query(
		  value="insert into comment_counsel(content,regdate,bcounselnum,username,wuser) values(?1,now(),?2,?3,?4)"
		  , nativeQuery=true)
		  public void insert(String content,Long bcounselnum, String username,Long wuser);

		 
	 
	
}
