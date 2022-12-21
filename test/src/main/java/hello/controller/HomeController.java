package hello.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.model.User;
import hello.repository.UserRepository;
import hello.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	@Autowired
	private UserService uService;
	@Autowired
	private UserRepository uRepository;
	
	@GetMapping("/")
	public String home() {
		return "join";
	}
	
	@GetMapping("join")
	public String join() {
		return "join";
	}
	
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody User user) {
		if(uRepository.findByUsername(user.getUsername())!=null) {
		return "fail";
		}
		uService.save(user);
		return "success";
	}

	@GetMapping("/login")
	public String login() {
    return "/login";
	}
	
	
	@GetMapping("detail/{userid}")
	public String detail(@PathVariable Long userid, Model model) {
		model.addAttribute("user", uService.detail(userid));
		return "detail";
	}
	
	@GetMapping("update/{userid}")
	public String update(@PathVariable Long userid, Model model) {
		model.addAttribute("user", uService.detail(userid));
		return "update";
	}
	
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody User user, HttpSession session) {
		uService.update(user);
		session.invalidate();
		return "success";
	}
	
	@DeleteMapping("delete/{userid}")
	@ResponseBody
	public Long delete(@PathVariable Long userid) {
		uService.delete(userid);
		return userid;
	}
	
}
	
