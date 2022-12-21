package hello.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.model.Doctor;
import hello.repository.DoctorRepository;
import hello.repository.UserRepository;
import hello.service.DoctorService;
import hello.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	private final UserService uService;
	private final UserRepository uRepository;
	private final DoctorService dService;
	private final DoctorRepository dRepository;
	
	@GetMapping("/admin/userlist")
	public String userlist(Model model) {
		List<hello.model.User> lists = uService.list();
		model.addAttribute("lists", lists);
		return "/admin/userlist";
	}
	
	@GetMapping("/admin/doctorlist")
	public String doctorlist(Model model) {
		List<Doctor> lists = dService.list();
		model.addAttribute("lists", lists);
		return "/admin/doctorlist";
	}
	
	@ResponseBody
	@PostMapping("/admin/drop")
	public String drop(@RequestBody Doctor doctor) {
		dService.drop(doctor);
		return "success";
	}
    
	@PostMapping("/admin/success")
	@ResponseBody
	public String success(@RequestBody Doctor doctor) {
		System.out.println(doctor);
		System.out.println("open! user sign success Id ajax!");
		dService.success(doctor);
		return "success";
	}
	
	@ResponseBody
	@PostMapping("/admin/fail")
	public String fail(@RequestBody Doctor doctor) {
		System.out.println(doctor);
		System.out.println("open! user sign failId Id ajax!");
		dService.fail(doctor);
		return "success";
	}

}
