package petcare.service;

import java.util.List;

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
	private CommentCounselRepository commentcRepository;
	@Autowired
	private BoardCounselRepository bcRepository;

	@Transactional
public void insert(CommentCounsel cscomment) {
	//	commentbcRepository.save(comment);				
	//	replycnt+1
		BoardCounsel b = bcRepository.findById(cscomment.getBCounsel().getCounselID()).get();
		b.setReplycnt(b.getReplycnt()+1);
		
		commentcRepository.insert(
		cscomment.getContent(),
		cscomment.getBCounsel().getCounselID());
			//cscomment.getDoctor().getDoctorID());
}


public List<CommentCounsel> list(Long bcounselnum) {
	return commentcRepository.findByNum(bcounselnum);
}

	/*
	 * @Transactional public void delete(Long comcounselID,CommentCounsel cscomment)
	 * { // 댓글개수감소 //Optional<CommentCounsel> c =
	 * commentcRepository.findById(comcounselID);// 위랑 동일, 풀어쓴 것 //BoardCounsel b =
	 * c.get().getBCounsel(); BoardCounsel b =
	 * bcRepository.findById(cscomment.getComcounselID()).get();
	 * b.setReplycnt(b.getReplycnt() - 1); // 댓글삭제
	 * commentcRepository.deleteById(comcounselID); }
	 */
}
