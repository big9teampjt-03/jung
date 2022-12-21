package hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hello.config.auth.PrincipalUser;
import hello.model.BoardCounsel;
import hello.model.BoardQuestion;
import hello.model.CommentCounsel;
import hello.model.CommentQuestion;
import hello.service.CommentCounselService;
import hello.service.CommentQuestionService;

@RestController
@RequestMapping("/reply/*")
public class CommentController {
	@Autowired
private CommentCounselService commentcsService;
	@Autowired
	private CommentQuestionService qcommentservice;

  //상담게시판댓글추가(인증된 회원만)
  @PostMapping("commentInsert/{counselID}") //counselID=게시글번호
  public ResponseEntity<String> commentInsert(@PathVariable Long counselID,
		  @RequestBody CommentCounsel cscomment,
		  @AuthenticationPrincipal PrincipalUser principal) {
  BoardCounsel b = new BoardCounsel(); 
  b.setCounselID(counselID);
  cscomment.setBCounsel(b);
  cscomment.setUser(principal.getUser());
  commentcsService.insert(cscomment);
  return new ResponseEntity<String>("success", HttpStatus.OK); }
 

  //상담게시판댓글전체보기
	@GetMapping("list/{counselID}")
	@ResponseBody
	public List<CommentCounsel> list(@PathVariable Long counselID) {
		// System.out.println("counselID:"+counselID);
		List<CommentCounsel> clist = commentcsService.list(counselID);
		return clist;
		 //return null;
	}
 
	
  //상담게시판댓글 삭제
  @DeleteMapping("delete/{comcounselID}") 
  public Long delete(@PathVariable Long comcounselID,CommentCounsel cscomment) {
  commentcsService.delete(comcounselID,cscomment); 
  return comcounselID; 
  }
 
  //////////////////////////////////////////////////////////////////////////////////////////
  
  //자유게시판댓글추가(인증된 회원만)
  @PostMapping("qcommentInsert/{questionID}") //questionID=게시글번호
  public ResponseEntity<String> qcommentInsert(@PathVariable Long questionID,
		  @RequestBody CommentQuestion qcomment,
		  @AuthenticationPrincipal PrincipalUser principal) {
  BoardQuestion b = new BoardQuestion(); 
  b.setQuestionID(questionID);
  qcomment.setBQuestion(b);
  qcomment.setUser(principal.getUser());
  qcommentservice.insert(qcomment);
  return new ResponseEntity<String>("success", HttpStatus.OK); }
 

  //자유게시판댓글전체보기
	@GetMapping("qclist/{questionID}")
	@ResponseBody
	public List<CommentQuestion> qlist(@PathVariable Long questionID) {
		List<CommentQuestion> qclist = qcommentservice.list(questionID);
		return qclist;
		 //return null;
	}
 
	
  //자유게시판댓글 삭제
  @DeleteMapping("qdelete/{comquestionID}") 
  public Long delete(@PathVariable Long comquestionID,CommentQuestion qcomment) {
	  qcommentservice.delete(comquestionID,qcomment); 
  return comquestionID; 
  }
  
}
