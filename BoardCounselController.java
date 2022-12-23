package hello.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.config.auth.PrincipalUser;
import hello.model.BoardCounsel;
import hello.service.BoardCounselService;

@RequestMapping("/board/*")
@Controller
public class BoardCounselController {
	@Autowired
	private BoardCounselService bcservice;
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@GetMapping({"get", "modify"})
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    public void get(@RequestParam Long counselID, @ModelAttribute("cri") Criteria cri, Model model) {
        log.info("get or modify.....");
        model.addAttribute("clist", bcservice.get(counselID));
        model.addAttribute("cri", cri);
    }
	
	//전문의상담게시판 페이징 검색 전체보기
		@GetMapping("boardCounsel")
		public String boardCounsel(Model model,
			 @PageableDefault(size=5,sort="counselID",direction=Direction.DESC) Pageable
			 pageable,
			 @RequestParam(required=false, defaultValue="")String field,
			 @RequestParam(required=false, defaultValue="")String word) {
			Page<BoardCounsel> lists =bcservice.findAll(field, word, pageable);
			Long count = bcservice.count(field,word);
			model.addAttribute("count",count);
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
	@PreAuthorize("isAuthenticated()")
	public String bcinsert() {
		return "/board/boardCounselinsert";
	}
	//전문의 상담게시판 글쓰기
	@PostMapping("boardCounselinsert")
	public String bcinsert(BoardCounsel board, 
			@AuthenticationPrincipal PrincipalUser principal,HttpSession session) {
		String uploadFolder=session.getServletContext().getRealPath("/")+"\\resources\\img";
		bcservice.boardCounselinsert(board,principal.getUser(),uploadFolder);
		return "redirect:boardCounsel";
	}
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
		bcservice.bcupdate(csboard);
	return "success";
	}
	//전문의 상담게시판 게시글삭제
	@DeleteMapping("bcdelete/{counselID}")
	@ResponseBody
	public String bcdelete(@PathVariable Long counselID) {
		bcservice.bcdelete(counselID);
		return "success";
	}
	
}
