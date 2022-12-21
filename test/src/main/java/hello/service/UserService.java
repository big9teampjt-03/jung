package hello.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hello.model.BoardCounsel;
import hello.model.BoardQuestion;
import hello.model.CommentCounsel;
import hello.model.CommentQuestion;
import hello.model.Role;
import hello.model.User;
import hello.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository uRepository;

	public void save(User user) {
		String rawPwd = user.getPassword();
		String encPwd = encoder.encode(rawPwd);
		user.setPassword(encPwd);
		user.setRole(Role.USER);
		uRepository.save(user);
	}
	
	public List<User> list() {
		return uRepository.findAll();
	}
	public Page<User> findAll(Pageable pageable) {
		Page<User> member = uRepository.findAll(pageable);// 아무 검색 없을때
		return member;
	}

	public User detail(Long userid) {
		return uRepository.findById(userid).get();
	}
	
	public void delete(Long userid) {
		uRepository.deleteById(userid);
	}
	
	@Transactional
	public void update(User user) {
	String rawPwd = user.getPassword();
	String encPwd = encoder.encode(rawPwd);
	User u = uRepository.findById(user.getUserid()).get();
	u.setNickname(user.getNickname());
	u.setTel(user.getTel());
	u.setUsername(user.getUsername());
	u.setPassword(encPwd);
	}
	
	public Long count() {
		return uRepository.count();
	}
	//마이페이지에서 상담게시글 뿌리기
		public List<BoardCounsel> findByID(Long wuser){
			return uRepository.findByUser(wuser);	
		}
		
		//마이페이지에서 자유게시판게시글 뿌리기
		public List<BoardQuestion> findByID2(Long wuser){
			return uRepository.findByUser2(wuser);
			}
		
		//마이페이지에서 상담댓글 뿌리기
		public List<CommentCounsel> findByID1(Long wuser){
			return uRepository.findByUser1(wuser);
		}
		//마이페이지에서 자유게시판댓글 뿌리기
		public List<CommentQuestion> findByID3(Long wuser){
			return uRepository.findByUser3(wuser);
				
			}

}