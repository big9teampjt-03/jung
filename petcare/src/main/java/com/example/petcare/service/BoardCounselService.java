package com.example.petcare.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.petcare.model.BoardCounsel;
import com.example.petcare.model.Member;
import com.example.petcare.repository.BoardCounselRepository;


@Transactional(readOnly = true)
@Service
public class BoardCounselService {
	@Autowired
	private BoardCounselRepository repository;

	@Transactional
	public void insert(BoardCounsel board, Member member) {
		board.setMember(member);
		repository.save(board);
	}

	/*
	 * 페에징 없는 전체보기 public List<Board> findAll(){ return repository.findAll();
	 * 
	 * }
	 */

	//페이징 포함 전체보기
	 public Page<BoardCounsel> findAll(Pageable pageable) {  
			Page<BoardCounsel> lists = repository.findAll(pageable);// 아무 검색 없을때
			  return lists; 
			  }
	
	// 페이징,검색 포함 전체보기
//	public Page<BoardCounsel> findAll(String field, String word, Pageable pageable) {
//
//		Page<BoardCounsel> lists = repository.findAll(pageable);// 아무 검색 없을때
//		if (field.equals("title")) {
//			lists = repository.findByTitleContaining(word, pageable);
//		} else if (field.equals("content")) {
//			lists = repository.findByContentContaining(word, pageable);
//		}
//
//		return lists;
//	}

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

	@Transactional
	public BoardCounsel findById(Long counselID) {
		BoardCounsel board = repository.findById(counselID).get();
		board.setHitcount(board.getHitcount() + 1);
		return board;

	}

	@Transactional 
	public void delete(Long counselID) {
		repository.deleteById(counselID);
	}

	@Transactional
	public void update(BoardCounsel board) {
		BoardCounsel b = repository.findById(board.getCounselID()).get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
		b.setRegdate(new Date());

	}
	
	
}
