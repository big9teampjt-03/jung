package hello.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.model.BoardCounsel;
import hello.model.BoardQuestion;
import hello.model.CommentCounsel;
import hello.model.CommentQuestion;
import hello.model.User;
import hello.repository.UserRepository;
import hello.service.UserService;
import lombok.RequiredArgsConstructor;

	@Controller
	@RequiredArgsConstructor
	public class UserController {
		private final UserService uService;
		private final UserRepository uRepository;
		
		@GetMapping("/user/userJoin")
		public String userjoin() {
			return "/user/userJoin";
		}
		
		@PostMapping("/user/userJoin")
		@ResponseBody
		public String userjoin(@RequestBody User user) {
			if(uRepository.findByUsername(user.getUsername())!=null) {
			return "fail";
			}
			uService.save(user);
			return "success";
		}
		
		//마이페이지폼
		@GetMapping("/userform/{userid}") 
		public String detail(@PathVariable Long userid,Model model) { 
			 User u =  uService.detail(userid);
			model.addAttribute("user",u); 
			return "/userform"; 
			}
	
		//마이페이지에서 상담게시글 전체보기
				@GetMapping("/boardcounsel/list/{userid}")
				@ResponseBody
				public List<BoardCounsel> list(@PathVariable Long userid) {
					List<BoardCounsel> user = uRepository.findByUser(userid);
					return user;
				}
				//마이페이지에서 상담댓글 전체보기
			@GetMapping("/commentcounsel/clist/{userid}")
			@ResponseBody
			public List<CommentCounsel> clist(@PathVariable Long userid) {
				List<CommentCounsel> user = uRepository.findByUser1(userid);
				return user;
			}
				//마이페이지에서 자유게시글 전체보기
				@GetMapping("/boardquestion/qlist/{userid}")
				@ResponseBody
				public List<BoardQuestion> qlist(@PathVariable Long userid) {
					List<BoardQuestion> user = uRepository.findByUser2(userid);
					return user;
				}
				
		
		//마이페이지에서 자유게시판댓글 전체보기
			@GetMapping("/commentquestion/qrlist/{userid}")//wuser=userid
			@ResponseBody
			public List<CommentQuestion> qrlist(@PathVariable Long userid) {
				List<CommentQuestion> user = uRepository.findByUser3(userid);
				return user;
			}
		
		@GetMapping("/user/userlist")
		public String list(Model model,
				@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
			Page<User> lists = uService.findAll(pageable);
			model.addAttribute("count", uService.count());
			model.addAttribute("users", lists);
			return "list";
		}
	
	}
