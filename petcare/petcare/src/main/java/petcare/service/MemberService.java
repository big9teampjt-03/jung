package petcare.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import petcare.model.Member;
import petcare.repository.MemberRepository;


@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	
	
	public Member detail(Long memberID) {
		return memberRepository.findById(memberID).get();
	}
	

	


}
	 
	 
	
	/*
	 * public Page<Member> findAll(Pageable pageable) { Page<Member> member =
	 * memberRepository.findAll(pageable);// 아무 검색 없을때 return member; }
	 */

