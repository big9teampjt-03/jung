package petcare.config.auth;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import petcare.model.Admin;
import petcare.model.Doctor;
import petcare.model.Member;
import petcare.repository.AdminRepository;
import petcare.repository.DoctorRepository;
import petcare.repository.MemberRepository;

@Service
public class PrincipalMemberDetails implements UserDetailsService {
	@Autowired
	private MemberRepository mRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loaduserByUsername");
		Member mb = mRepository.findByUsername(username);
		if(mb==null) return null;
		PrincipalMember pMember = new PrincipalMember(mb);
		System.out.println("pMember:"+pMember);
		return pMember;
	}
}