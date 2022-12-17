package petcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import petcare.model.Doctor;
import petcare.repository.DoctorRepository;
import petcare.service.DoctorService;

@RequestMapping("/doctor/*")
@Controller
@RequiredArgsConstructor
public class DoctorController {
	private final DoctorService dService;
	private final DoctorRepository dRepository;

//	@GetMapping("/login")
//	public String login() {
//		return "/login";
//	}
	
	@GetMapping("/doctor/doctorJoin")
	public String memberjoin() {
		return "/doctor/doctorJoin";
	}
	
	@PostMapping("/doctor/doctorJoin")
	@ResponseBody
	public String memberjoin(@RequestBody Doctor doctor) {
		if(dRepository.findByUsername(doctor.getUsername())!=null) {
		return "fail";
		}
		dService.save(doctor);
		return "success";
	}
	
	@GetMapping("doctorform/{doctorID}") 
	public String detail(@PathVariable Long doctorID,Model model) { 
		model.addAttribute("doctor",dService.detail(doctorID)); 
		return "doctor/doctorform"; 
		}
}

