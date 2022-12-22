package hello.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import hello.model.BoardQuestion;
import hello.model.User;
import hello.repository.BoardQuestionRepository;

@Transactional(readOnly = true) 
@Service
public class BoardQuestionService {
	@Autowired
	private BoardQuestionRepository repository;

	@Transactional
	public void boardQuestioninsert(BoardQuestion board,User user,String uploadFolder) {
		UUID uuid = UUID.randomUUID();
		MultipartFile b= board.getUploadquestion();
		String uploadFileName="";
		if(!b.isEmpty()) {
			uploadFileName=uuid.toString()+"_"+b.getOriginalFilename();
			File saveFile=new File(uploadFolder,uploadFileName);
			try {
				b.transferTo(saveFile);
				board.setQuestionimage(uploadFileName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		board.setUser(user);
		repository.save(board);
	}

	/*
	 * 페에징 없는 전체보기 public List<Board> findAll(){ return repository.findAll();
	 * 
	 * }
	 */
	//페이징 포함 전체보기
	 public Page<BoardQuestion> findAll(Pageable pageable) {  
			Page<BoardQuestion> lists = repository.findAll(pageable);// 아무 검색 없을때
			  return lists; 
			  }
	// 페이징,검색 포함 전체보기
	public Page<BoardQuestion> findAll(String field, String word, Pageable pageable) {

		Page<BoardQuestion> lists = repository.findAll(pageable);// 아무 검색 없을때
		if (field.equals("title")) {
			lists = repository.findByTitleContaining(word, pageable);
		} else if (field.equals("content")) {
			lists = repository.findByContentContaining(word, pageable);
		}

		return lists;
	}

	/*
	 * public Long count(String field, String word) { Long count =
	 * repository.count(); if (field.equals("title")) { count =
	 * repository.cntTitleSearch(word); } else if (field.equals("content")) { count
	 * = repository.cntContentSearch(word); }
	 * 
	 * return count; }
	 */

	public Long count() {
		return repository.count();
	}
	
	 public Long count(String field, String word) {
			Long count = repository.count();
			if(field.equals("title")) {
				count=repository.cntTitleSearch(word);
			}else if (field.equals("content")){
				count=repository.cntContentSearch(word);
			}
			return count;
		}

	@Transactional
	public BoardQuestion findById(Long questionID) {
		BoardQuestion board = repository.findById(questionID).get();
		board.setHitcount(board.getHitcount() + 1);
		return board;

	}

	public BoardQuestion detail(Long questionID) {
		return repository.findById(questionID).get();
	}
	@Transactional 
	public void delete(Long questionID) {
		repository.deleteById(questionID);
	}

	@Transactional
	public void bqupdate(BoardQuestion board) {
		BoardQuestion b = repository.findById(board.getQuestionID()).get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
		b.setRegdate(new Date());

	}

	
}
