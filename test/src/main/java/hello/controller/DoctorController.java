package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.config.auth.PrincipalUser;
import hello.model.Doctor;
import hello.model.User;
import hello.service.DoctorService;

@Controller
public class DoctorController {
	
	@Autowired
	private DoctorService dService;
	
	@GetMapping("docupdate/{userid}")
	public String docupdate() {
		return "docupdate";
	}
	
	@PostMapping("docupdate/{userid}")
	@ResponseBody
	public ResponseEntity<String> docinsert(@PathVariable Long userid, 
			@RequestBody Doctor doctor,
			@AuthenticationPrincipal PrincipalUser principal) {
		User u = new User();
		u.setUserid(userid);
		doctor.setUser(u);
		doctor.setUser(principal.getUser());
		dService.insert(doctor);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}

}
