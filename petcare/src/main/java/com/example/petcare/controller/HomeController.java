package com.example.petcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.petcare.model.BoardCounsel;
import com.example.petcare.model.Member;
import com.example.petcare.service.BoardCounselService;
import com.example.petcare.service.MemberService;



@Controller
// @RequiredArgsConstructor
public class HomeController {
	@Autowired
	BoardCounselService bcservice;
	@Autowired
	private MemberService memberservice;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	

}
