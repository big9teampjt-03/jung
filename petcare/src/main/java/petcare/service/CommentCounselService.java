package petcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import petcare.model.BoardCounsel;
import petcare.model.CommentCounsel;
import petcare.repository.BoardCounselRepository;
import petcare.repository.CommentCounselRepository;

@Service
public class CommentCounselService {
	@Autowired
	private CommentCounselRepository commentbcRepository;
	@Autowired
	private BoardCounselRepository bcRepository;

	@Transactional
public void insert(CommentCounsel cscomment) {
	//	commentbcRepository.save(comment);				
	//	replycnt+1
		BoardCounsel b = bcRepository.findById(cscomment.getBCounsel().getCounselID()).get();
		b.setReplycnt(b.getReplycnt()+1);
		
		commentbcRepository.insert(
		cscomment.getContent(),
		cscomment.getBCounsel().getCounselID(),
			cscomment.getDoctor().getDoctorID());
}

	public List<CommentCounsel> list(Long counselID) {
		return commentbcRepository.findById(counselID);
	}

	@Transactional
	public void delete(Long comcounselID) {
		// 댓글개수감소
		Optional<CommentCounsel> c = commentbcRepository.findById(comcounselID);// 위랑 동일, 풀어쓴 것
		BoardCounsel b = c.get().getBCounsel();
		b.setReplycnt(b.getReplycnt() - 1);
		// 댓글삭제
		commentbcRepository.deleteById(comcounselID);
	}
}
