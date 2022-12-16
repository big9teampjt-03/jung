package petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import petcare.model.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	Doctor findByUsername(String username);
}
