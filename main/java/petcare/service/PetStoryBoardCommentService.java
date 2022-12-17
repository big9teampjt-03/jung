package petcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import petcare.model.BoardPetstory;
import petcare.model.CommentPetstory;
import petcare.repository.PetStoryBoardCommentRepository;
import petcare.repository.PetStoryBoardRepository;

@Service
public class PetStoryBoardCommentService {

	@Autowired
	private PetStoryBoardCommentRepository petStoryBoardCommentRepository;
	@Autowired
	private PetStoryBoardRepository petStoryBoardRepository;
	
	@Transactional
	public void insert(CommentPetstory petStoryBoardComment) {
		
		BoardPetstory bpsboard = petStoryBoardRepository.findById(petStoryBoardComment.getBPetstoryl().getPetstoryID()).get();
		bpsboard.setReplycnt(bpsboard.getReplycnt() + 1); 
		petStoryBoardCommentRepository.insert(petStoryBoardComment.getContent(),
											  petStoryBoardComment.getBPetstoryl().getPetstoryID());
											  //petStoryBoardComment.getUsername());
		
}

 // public List<CommentPetstory> list(Long petstoryID) { 
//	  return petStoryBoardCommentRepository.findByPetStoryId(petstoryID); }
 
}

