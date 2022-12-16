package petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import petcare.model.Member;
import petcare.service.DoctoreService;
import petcare.service.MemberService;

@RequestMapping("/doctor/*")
@Controller
public class DoctorController {
	@Autowired
	private DoctoreService doctorservice;
	
	@GetMapping("doctorform/{doctorID}") 
	public String detail(@PathVariable Long doctorID,Model model) { 
		model.addAttribute("doctor",doctorservice.detail(doctorID)); 
		return "doctor/doctorform"; 
		}
}
