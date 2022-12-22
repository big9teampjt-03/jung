package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hello.model.BoardCounsel;
import hello.model.BoardPetstory;
import hello.model.BoardQuestion;
import hello.model.CommentCounsel;
import hello.model.CommentPetStory;
import hello.model.CommentQuestion;
import hello.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@EntityGraph(attributePaths = "role")
	User findByUsername(String username);
	
	@Query("select sc from board_counsel sc join fetch sc.user where wuser=?1")
	public List<BoardCounsel> findByUser(Long wuser);
	
	@Query("select sc from comment_counsel sc join fetch sc.user where wuser=?1")
	public List<CommentCounsel> findByUser1(Long wuser);
	
	@Query("select sc from board_question sc join fetch sc.user where wuser=?1")
	public List<BoardQuestion> findByUser2(Long wuser);
	
	@Query("select sc from comment_question sc join fetch sc.user where wuser=?1")
	public List<CommentQuestion> findByUser3(Long wuser);

	@Query("select sc from bPetstoryl sc join fetch sc.user where wuser=?1")
	public List<BoardPetstory> findByUser4(Long userid);
	
	@Query("select sc from comment_pet_story sc join fetch sc.user where wuser=?1")
	public List<CommentPetStory> findByUser5(Long wuser);
}
