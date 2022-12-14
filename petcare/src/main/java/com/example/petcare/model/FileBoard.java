package com.example.petcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class FileBoard {//대문자로 하면 디비에 file_board로 들어가짐
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long num;
private String title;
private String writer;//작성자는 사실 필요없음 이것도 board와 comment의 작성자와 외래키를 잡아야 하는데 지금 안하는듯??
private String content;
@Transient
private MultipartFile upload;//업로드할 파일, 디비에 컬럼으로 들어갈 필요 없고 들어가지도 못함 디비에 들어갈 컬럼에서 제외시켜야함 @Transient필요.
private String fileimage;//테이블에 저장할 파일이름
}
