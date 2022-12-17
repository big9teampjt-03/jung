package petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import petcare.model.CommentPetstory;

public interface PetStoryBoardCommentRepository extends JpaRepository<CommentPetstory, Long>{
	@Modifying
	@Query(value = "insert into comment_pet_story(content, regdate, bpetstorynum) values(?1,now(), ?2)",nativeQuery = true)
	public void insert(String content, Long bum);
	
	 //@Query("select cps from comment_pet_story cps where petstoryID=?1") public
	// @Query("select cps from comment_pet_story cps join fetch cps.bPetstoryl where bpetstorynum=?1")
	// List<CommentPetstory> findByPetStoryId(Long petstoryID);

}

