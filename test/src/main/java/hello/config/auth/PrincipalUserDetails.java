package hello.config.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hello.model.Role;
import hello.model.User;
import hello.repository.UserRepository;

@Service
public class PrincipalUserDetails implements UserDetailsService {

	// private Logger logger = LoggerFactory.getLogger(PrincipalUserDetails.class);

	private UserRepository uRepository;

	@Autowired
	public PrincipalUserDetails(UserRepository uRepository) {
		this.uRepository = uRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = uRepository.findByUsername(username);
		if (user == null)
			return null;
		PrincipalUser pUser = new PrincipalUser(user);
		// if(!pUser.isPresent()) {
		// logger.info("존재하지 않는 아이디입니다: " + username);
		// throw new UsernameNotFoundException(username);
		return pUser;
		// return new PrincipalUser(pUser.get());
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("loaduserByUsername");
//		User user = uRepository.findByUsername(username);
//		if(user==null) return null;
//		PrincipalUser pUser = new PrincipalUser(user);
//		 System.out.println("pUser:"+pUser);
//		 return pUser;

//		List<GrantedAuthority> authorities = new ArrayList<>();
//
//		if (("admin@admin.com").equals(username)) {
//		authorities.add(new SimpleGrantedAuthority(Role.ADMIN.value()));
//		} else {
//		authorities.add(new SimpleGrantedAuthority(Role.USER.value()));
//		}
//		System.out.println("pUser:"+pUser);
//		System.out.println(authorities);
//		return pUser;
}
