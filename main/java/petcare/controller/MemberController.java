package petcare.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import petcare.model.Member;
import petcare.repository.MemberRepository;
import petcare.service.MemberService;

	@Controller
	@RequiredArgsConstructor
	public class MemberController {
		private final MemberService mService;
		private final MemberRepository mRepository;
		
		@GetMapping("/member/memberJoin")
		public String memberjoin() {
			return "/member/memberJoin";
		}
		
		@PostMapping("/member/memberJoin")
		@ResponseBody
		public String memberjoin(@RequestBody Member member) {
			if(mRepository.findByUsername(member.getUsername())!=null) {
			return "fail";
			}
			mService.save(member);
			return "success";
		}

		@GetMapping("/member/memberform/{memberID}") 
		public String detail(@PathVariable Long memberID,Model model) { 
			model.addAttribute("member",mService.detail(memberID)); 
			return "/member/memberform"; 
			}
//		@GetMapping("/login")
//		public String login() {
//			return "login";
//		}
//		
		
		
		@GetMapping("/member/memberlist")
		public String list(Model model,
				@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
			Page<Member> lists = mService.findAll(pageable);
			model.addAttribute("count", mService.count());
			model.addAttribute("members", lists);
			return "list";
		}
	}
