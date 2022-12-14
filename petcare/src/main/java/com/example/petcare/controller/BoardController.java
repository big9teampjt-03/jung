package com.example.petcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.petcare.model.BoardCounsel;
import com.example.petcare.model.BoardQuestion;
import com.example.petcare.model.Member;
import com.example.petcare.service.BoardCounselService;
import com.example.petcare.service.BoardQuestionService;

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
		 @PageableDefault(size=2,sort="counselID",direction=Direction.DESC) Pageable
		 pageable) {
		Page<BoardCounsel> lists =bcservice.findAll(pageable);
		model.addAttribute("clists",lists);
		return "/board/boardCounsel";
	}
	//전문의상담게시판 상세보기
	@GetMapping("boardCounselview/{counselID}")
	public String view(@PathVariable Long counselID, Model model) {
		model.addAttribute("board",bcservice.findById(counselID));
		return "/board/boardCounselview";
	}
	//자유게시판 전체보기
	@GetMapping("boardQuestion")
	public String boardQuestion(Model model,
		 @PageableDefault(size=2,sort="questionID",direction=Direction.DESC) Pageable
		 pageable) {
		Page<BoardQuestion> lists =bqservice.findAll(pageable);
		model.addAttribute("qlists",lists);
		return "/board/boardQuestion";
	}
	@GetMapping("boardCounselinsert")
	//@PreAuthorize("isAuthenticated()")
	public String insert() {
		return "/board/boardCounselinsert";
	}

	@PostMapping("boardCounselinsert")
	public String insert(BoardCounsel board, Member member) {
		bcservice.insert(board,member);
		return "redirect:boardCounselinsert";
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
}
