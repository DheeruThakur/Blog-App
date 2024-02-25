package com.blogapplication.serviceImpl;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapplication.services.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uplaodImage(String path, MultipartFile file) throws IOException {
		
		String originalFilename = file.getOriginalFilename();
		
		String randomString = UUID.randomUUID().toString();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String filename = randomString + extension;
	
		String fullPath = path + File.separator + filename;

		File directory = new File(fullPath);
	
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		Files.copy(file.getInputStream() , Paths.get(fullPath) , StandardCopyOption.REPLACE_EXISTING);
		
		return filename;
	}
	

	@Override
	public InputStream getResource(String path, String imageName) throws FileNotFoundException {
		String fullPath = path + File.separator + imageName;
		InputStream iStream = new FileInputStream(fullPath);
		return iStream;
	}


}
