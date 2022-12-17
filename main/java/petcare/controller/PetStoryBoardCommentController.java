package petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import petcare.model.BoardPetstory;
import petcare.model.CommentPetstory;
import petcare.service.PetStoryBoardCommentService;

@RestController
@RequestMapping("/reply/*")
public class PetStoryBoardCommentController {
	@Autowired
	private PetStoryBoardCommentService petStoryBoardCommentService;
	
//댓글 추가
@PostMapping("insert/{petstory_id}")
public ResponseEntity<String> commentInsert(@PathVariable Long petstoryID,
											@RequestBody CommentPetstory commentPetStory){

	BoardPetstory bpsboard = new BoardPetstory();
	bpsboard.setPetstoryID(petstoryID);
	commentPetStory.setBPetstoryl(bpsboard);
	petStoryBoardCommentService.insert(commentPetStory);
	return new ResponseEntity<String>("success",HttpStatus.OK);
}


  //댓글 전체보기
  
//  @GetMapping("list/{petstory_id}") 
//  @ResponseBody
 // public List<CommentPetstory> list(@PathVariable Long petstoryID){ 
//	  List<CommentPetstory> cpslist = petStoryBoardCommentService.list(petstoryID); 
//	  System.out.println(cpslist.size());
//	  return cpslist; 
//	  }
 

}
