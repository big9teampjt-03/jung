package hello.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.config.auth.PrincipalUser;
import hello.model.BoardQuestion;
import hello.service.BoardQuestionService;


@RequestMapping("/board/*")
@Controller
public class BoardQuestionController {
	
	@Autowired
	private BoardQuestionService bqservice;
	
	//자유게시판 페이징 검색 전체보기
	@GetMapping("boardQuestion")
	public String boardQuestion(Model model,
		 @PageableDefault(size=5,sort="questionID",direction=Direction.DESC) Pageable
		 pageable,
		 @RequestParam(required=false, defaultValue="")String field,
		 @RequestParam(required=false, defaultValue="")String word)  {
		Page<BoardQuestion> lists =bqservice.findAll(field,word,pageable);
		Long count = bqservice.count(field,word);
		model.addAttribute("count",count);
		model.addAttribute("qlists",lists);
		return "/board/boardQuestion";
	}
	//자유게시판 상세보기
		@GetMapping("boardQuestionview/{questionID}")
		public String bqview(@PathVariable Long questionID, Model model) {
			model.addAttribute("board",bqservice.findById(questionID));
			return "/board/boardQuestionview";
		}
		//자유게시판 글쓰기폼
		@GetMapping("boardQuestioninsert")
		@PreAuthorize("isAuthenticated()")
		public String bqinsert() {
			return "/board/boardQuestioninsert";
		}
		//자유게시판 글쓰기
		@PostMapping("boardQuestioninsert")
		public String bqinsert(BoardQuestion board, 
				@AuthenticationPrincipal PrincipalUser principal,
				HttpSession session) {
			String uploadFolder=session.getServletContext().getRealPath("/")+"\\resources\\img";
			bqservice.boardQuestioninsert(board,principal.getUser(),uploadFolder);
			return "redirect:boardQuestion";
		}
		//자유게시판 수정폼
		@GetMapping("boardQuestionupdate/{questionID}")
		public String bqupdate(@PathVariable Long questionID, Model model) {
			model.addAttribute("board", bqservice.detail(questionID));
			return "board/boardQuestionupdate";
		}
		//자유게시판 수정하기
		@PutMapping("boardQuestionupdate")
		@ResponseBody
		public String bqupdate(@RequestBody BoardQuestion qboard, HttpSession session) {
			bqservice.bqupdate(qboard);
		return "success";
		}
		//자유게시판 게시글삭제
		@DeleteMapping("bqdelete/{questionID}")
		@ResponseBody
		public String bqdelete(@PathVariable Long questionID) {
			bqservice.delete(questionID);
			return "success";
		}
	
}