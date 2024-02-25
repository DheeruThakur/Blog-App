package com.blogapplication.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uplaodImage(String path , MultipartFile file) throws IOException;
	InputStream getResource(String path , String imageName) throws FileNotFoundException;
}