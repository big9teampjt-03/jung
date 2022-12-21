package hello.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.model.BoardCounsel;
import hello.model.CommentCounsel;
import hello.repository.BoardCounselRepository;
import hello.repository.CommentCounselRepository;

@Service
public class CommentCounselService {
	@Autowired
	private CommentCounselRepository commentcRepository;
	@Autowired
	private BoardCounselRepository bcRepository;

	@Transactional
public void insert(CommentCounsel cscomment) {
		BoardCounsel b = bcRepository.findById(cscomment.getBCounsel().getCounselID()).get();
		b.setReplycnt(b.getReplycnt()+1);
		
		commentcRepository.insert(
		cscomment.getContent(),
		cscomment.getBCounsel().getCounselID(),
		cscomment.getUser().getUsername(),
		cscomment.getUser().getUserid());
			//cscomment.getDoctor().getDoctorID());
}


public List<CommentCounsel> list(Long bcounselnum) {
	return commentcRepository.findByNum(bcounselnum);
}

	
	  @Transactional 
	  public void delete(Long comcounselID,CommentCounsel cscomment)
	  { 
		  // 댓글개수감소 //
		  Optional<CommentCounsel> c = commentcRepository.findById(comcounselID);
		  BoardCounsel b = c.get().getBCounsel(); 
	  b.setReplycnt(b.getReplycnt() - 1); // 댓글삭제
	  commentcRepository.deleteById(comcounselID); 
	  }
	 
}
