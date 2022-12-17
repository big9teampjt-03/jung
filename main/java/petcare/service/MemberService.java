package petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import petcare.model.Member;
import petcare.model.Role;
import petcare.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private MemberRepository mRepository;

	public void save(Member member) {
		String rawPwd = member.getPassword();
		String encPwd = encoder.encode(rawPwd);
		member.setPassword(encPwd);
		member.setRole(Role.MEMBER);
		mRepository.save(member);
	}

	public Page<Member> findAll(Pageable pageable) {
		Page<Member> member = mRepository.findAll(pageable);// 아무 검색 없을때
		return member;
	}

	public Long count() {
		return mRepository.count();
	}
	
	public Member detail(Long member_id) {
		return mRepository.findById(member_id).get();
	}

	/*
	 * public List<User>list(){ return userRepository.findAll();//findAll은 전체를 보게하는
	 * 함수 이름. 내장되어있음 } }
	 */

}
