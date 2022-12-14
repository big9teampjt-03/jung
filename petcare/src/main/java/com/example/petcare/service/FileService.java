package com.example.petcare.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.petcare.model.FileBoard;
import com.example.petcare.repository.FileRepository;



@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	//webapp-resources-img 경로
public void fileInsert(FileBoard fboard, String uploadFolder) {
	UUID uuid = UUID.randomUUID();
	MultipartFile f= fboard.getUpload();
	String uploadFileName="";
	if(!f.isEmpty()) {
		uploadFileName=uuid.toString()+"_"+f.getOriginalFilename();
		File saveFile=new File(uploadFolder,uploadFileName);
		try {
			f.transferTo(saveFile);
			fboard.setFileimage(uploadFileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	fileRepository.save(fboard);
}

public List<FileBoard> list() {
	return fileRepository.findAll();
}
//static-images에 저장, properties에서 경로 지정해주기
public void fileInsert(FileBoard fboard) {
UUID uuid = UUID.randomUUID();
MultipartFile f= fboard.getUpload();
String uploadFileName="";
if(!f.isEmpty()) {
	uploadFileName=uuid.toString()+"_"+f.getOriginalFilename();
	File saveFile=new File(uploadFileName);
	try {
		f.transferTo(saveFile);
		fboard.setFileimage(uploadFileName);
	} catch (IllegalStateException | IOException e) {
		e.printStackTrace();
	}
}
fileRepository.save(fboard);
}

}
