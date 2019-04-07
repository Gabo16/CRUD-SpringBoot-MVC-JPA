package com.springboot.app.models.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadImageService {
	
	public String load(MultipartFile foto);

	public boolean delete(String filename);

}
