package petcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequiredArgsConstructor
public class HomeController {
	
	@GetMapping("/")
	public String home(){
		return "home";
	}
	@GetMapping("/member/loginPro")
	public String login() {
		return "/member/login";
	}
//	@PostMapping("/member/login")
//	public String logi() {
//		return "redirect:/home";
//	}
	@GetMapping("/member/join")
	public String join() {
		return "/member/join";
	}
}