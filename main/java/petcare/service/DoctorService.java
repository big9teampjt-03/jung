package petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import petcare.model.Doctor;
import petcare.model.Role;
import petcare.repository.DoctorRepository;


@Service
@Transactional
public class DoctorService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private DoctorRepository dRepository;
	
	public void save(Doctor doctor) {
		String rawPwd = doctor.getPassword();
		String encPwd = encoder.encode(rawPwd);
		doctor.setPassword(encPwd);
		doctor.setRole(Role.DOCTOR);
		dRepository.save(doctor);
	}
	
	
	public Doctor detail(Long doctor_id) {
		return dRepository.findById(doctor_id).get();
	}
	
	/*
	 * public List<User>list(){ return userRepository.findAll();//findAll은 전체를 보게하는
	 * 함수 이름. 내장되어있음 } }
	 */
	 
	
	  public Page<Doctor> findAll(Pageable pageable) {  
	Page<Doctor> doctor = dRepository.findAll(pageable);// 아무 검색 없을때
	  return doctor; 
	  }
	 
}
