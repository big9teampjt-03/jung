package petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import petcare.model.FileBoard;

public interface FileRepository extends JpaRepository<FileBoard, Long>{

}
