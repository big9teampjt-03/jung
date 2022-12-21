package hello.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.model.BoardQuestion;
import hello.model.CommentQuestion;
import hello.repository.BoardQuestionRepository;
import hello.repository.CommentQuestionRepository;

@Service
public class CommentQuestionService {
	@Autowired
	private CommentQuestionRepository commentqRepository;
	@Autowired
	private BoardQuestionRepository bqRepository;

	@Transactional
public void insert(CommentQuestion qcomment) {
		BoardQuestion b = bqRepository.findById(qcomment.getBQuestion().getQuestionID()).get();
		b.setReplycnt(b.getReplycnt()+1);
		
		commentqRepository.insert(
				qcomment.getContent(),
				qcomment.getBQuestion().getQuestionID(),
				qcomment.getUser().getUsername(),
				qcomment.getUser().getUserid());
			//cqcomment.getDoctor().getDoctorID());
}


public List<CommentQuestion> list(Long bquestionnum) {
	return commentqRepository.findByNum(bquestionnum);
}

	
	  @Transactional 
	  public void delete(Long comquestionID,CommentQuestion cqcomment)
	  { 
		  // 댓글개수감소 //
		  Optional<CommentQuestion> c = commentqRepository.findById(comquestionID);// 위랑 동일, 풀어쓴 것 
		  BoardQuestion b = c.get().getBQuestion(); 
	  b.setReplycnt(b.getReplycnt() - 1); // 댓글삭제
	  commentqRepository.deleteById(comquestionID); 
	  }
	 
}
