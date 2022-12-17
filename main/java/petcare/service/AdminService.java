package petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import petcare.model.Admin;
import petcare.model.Role;
import petcare.repository.AdminRepository;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private AdminRepository aRepository;
	
	public void save(Admin admin) {
		String rawPwd = admin.getPassword();
		String encPwd = encoder.encode(rawPwd);
		admin.setPassword(encPwd);
		admin.setRole(Role.ADMIN);
		aRepository.save(admin);
	}

}
