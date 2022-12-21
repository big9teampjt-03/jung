package hello.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hello.model.Doctor;

public interface DoctorRepository 
	extends JpaRepository<Doctor, Long>{
	
	@Modifying
	@Query(value="insert into doctor (docname, doclicensenum, dochospital, dochospitaltel, dochospitaladdr, dochospitalpostal, user_id, role, regdate) values(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", 
	nativeQuery = true)
	public void insert(String docname, String doclicensenum, String dochospital, String dochospitaltel, String dochospitaladdr, String dochospitalpostal, Long user_id, String role, Date regdate);
	
	

}
