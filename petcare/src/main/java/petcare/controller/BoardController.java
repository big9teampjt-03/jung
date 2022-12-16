package petcare.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import petcare.model.BoardCounsel;
import petcare.model.BoardQuestion;
import petcare.model.Member;
import petcare.service.BoardCounselService;
import petcare.service.BoardQuestionService;
import petcare.service.MemberService;


@RequestMapping("/board/*")
@Controller
public class BoardController {
	@Autowired
	private BoardCounselService bcservice;
	@Autowired
	private BoardQuestionService bqservice;
	
	//전문의상담게시판 전체보기
	@GetMapping("boardCounsel")
	public String boardCounsel(Model model,
		 @PageableDefault(size=5,sort="counselID",direction=Direction.DESC) Pageable
		 pageable) {
		Page<BoardCounsel> lists =bcservice.findAll(pageable);
		model.addAttribute("clists",lists);
		return "/board/boardCounsel";
	}
	//전문의상담게시판 상세보기
	@GetMapping("boardCounselview/{counselID}")
	public String bcview(@PathVariable Long counselID, Model model) {
		model.addAttribute("board",bcservice.findById(counselID));
		return "/board/boardCounselview";
	}
	//전문의 상담게시판 글쓰기폼
	@GetMapping("boardCounselinsert")
	//@PreAuthorize("isAuthenticated()")
	public String bcinsert() {
		return "/board/boardCounselinsert";
	}
	//전문의 상담게시판 글쓰기
	@PostMapping("boardCounselinsert")
	public String bcinsert(BoardCounsel board, HttpSession session) {
		String uploadFolder=session.getServletContext().getRealPath("/")+"\\resources\\img";
		bcservice.boardCounselinsert(board,uploadFolder);
		return "redirect:boardCounsel";
	}
	/*
	 * //인증된 회원만 게시글 작성하기
	 * 
	 * @GetMapping("boardCounselinsert")
	 * 
	 * @PreAuthorize("isAuthenticated()") public String insert() { return
	 * "/board/boardCounselinsert"; }
	 * 
	 * @PostMapping("boardCounselinsert") public String insert(BoardCounsel board,
	 * 
	 * @AuthenticationPrincipal PrincipalUser principal) { bcservice.insert(board,
	 * principal.getUser()); return "redirect:boardCounselinsert"; }
	 */
	//전문의 상담게시판 수정폼
	@GetMapping("boardCounselupdate/{counselID}")
	public String bcupdate(@PathVariable Long counselID, Model model) {
		model.addAttribute("board", bcservice.detail(counselID));
		return "board/boardCounselupdate";
	}
	//전문의 상담게시판 수정하기
	@PutMapping("boardCounselupdate")
	@ResponseBody
	public String bcupdate(@RequestBody BoardCounsel csboard, HttpSession session) {
		String uploadFolder=session.getServletContext().getRealPath("/")+"\\resources\\img";
		bcservice.bcupdate(csboard,uploadFolder);
	return "success";
	}
	//전문의 상담게시판 게시글삭제
	@DeleteMapping("bcdelete/{counselID}")
	@ResponseBody
	public String bcdelete(@PathVariable Long counselID) {
		bcservice.bcdelete(counselID);
		return "success";
	}
	
	/////////////////////////////////////////////////////////////////////////////
	//자유게시판 전체보기
	@GetMapping("boardQuestion")
	public String boardQuestion(Model model,
		 @PageableDefault(size=5,sort="questionID",direction=Direction.DESC) Pageable
		 pageable) {
		Page<BoardQuestion> lists =bqservice.findAll(pageable);
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
		//@PreAuthorize("isAuthenticated()")
		public String bqinsert() {
			return "/board/boardQuestioninsert";
		}
		//자유게시판 글쓰기
		@PostMapping("boardQuestioninsert")
		public String bqinsert(BoardQuestion board, HttpSession session) {
			String uploadFolder=session.getServletContext().getRealPath("/")+"\\resources\\img";
			bqservice.boardQuestioninsert(board,uploadFolder);
			return "redirect:boardQuestion";
		}
		//자유게시판 수정폼
		@GetMapping("boardQuestionupdate/{questionID}")
		public String bqupdate(@PathVariable Long questionID, Model model) {
			model.addAttribute("board", bcservice.detail(questionID));
			return "board/boardQuestionupdate";
		}
		//자유게시판 수정하기
		@PutMapping("boardQuestionupdate")
		@ResponseBody
		public String bqupdate(@RequestBody BoardQuestion qboard, HttpSession session) {
			//String uploadFolder=session.getServletContext().getRealPath("/")+"\\resources\\img";
			bqservice.bqupdate(qboard);
		return "success";
		}
		//자유게시판 게시글삭제
		@DeleteMapping("bqdelete/{questionID}")
		@ResponseBody
		public String bqdelete(@PathVariable Long questionID) {
			bqservice.bqdelete(questionID);
			return "success";
		}
	
}