package petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import petcare.model.Member;
import petcare.service.MemberService;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	@Autowired
	private MemberService memberservice;
	
	@GetMapping("memberform/{memberID}") 
	public String detail(@PathVariable Long memberID,Model model) { 
		model.addAttribute("member",memberservice.detail(memberID)); 
		return "member/memberform"; 
		}
}
