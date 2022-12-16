package petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import petcare.model.BoardCounsel;
import petcare.model.CommentCounsel;
import petcare.service.CommentCounselService;


@RestController
@RequestMapping("/reply/*")
public class CommentController {
	@Autowired
private CommentCounselService commentcsService;
	
	
	//commentInsert
		@PostMapping("commentInsert")
		@ResponseBody
		public String commentInsert(@RequestBody CommentCounsel comment) {
			comment.setUsername("aa");
			commentcsService.insert(comment);
			return "success";
		}
	
	
/*
 * //댓글추가(인증된 회원만)
 * 
 * @PostMapping("insert/{num}") public ResponseEntity<String>
 * commentInsert(@PathVariable Long num,
 * 
 * @RequestBody CommentCounsel cscomment,
 * 
 * @AuthenticationPrincipal PrincipalUser principal) {//view에서 bnum과 content를
 * 받음, db에는 bnum 안들어가지는 문제 발생, comment는 Board형으로 들어가야함
 * 
 * BoardCounsel b = new BoardCounsel(); b.setCounselID(id);//boardcounsel
 * counselid cscomment.setBCounsel(b);//이제comment에 bnum이 들어가게 했음 이렇게 해야 bnum이
 * 들어가짐 //// //PrincipalUser p=(PrincipalUser)
 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();//get이
 * holder를 반환시킴,authencation이 인증된걸 가져오고 getprincipal 오브젝트를 가져옴 앞에 principaluser를
 * 넣어야 인증된 유저를 가져오는것 //comment.setUser(p.getUser());
 * 
 * cscomment.setUser(principal.getUser());//@AuthenticationPrincipal추가할때 쓰는 것
 * comment에 userid가 기본키로 잡혀있어 외래키를 이용하는 것 commentService.insert(cscomment);
 * return new ResponseEntity<String>("success", HttpStatus.OK);//상태값을 문자형으로
 * success를 ok상태로 리턴 }
 */
	//댓글전체보기
	@GetMapping("list/{num}")
	public List<CommentCounsel> list(@PathVariable Long counselID) {
		//System.out.println("counselID:"+counselID);
		List<CommentCounsel>clist=commentcsService.list(counselID);
		return clist;
	}
	
	//댓글 삭제
		@DeleteMapping("delete/{comcounselID}")
		public Long delete(@PathVariable Long comcounselID) {
			commentcsService.delete(comcounselID);
			return comcounselID;
		}
}
