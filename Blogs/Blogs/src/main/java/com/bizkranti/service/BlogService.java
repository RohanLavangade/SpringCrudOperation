package com.bizkranti.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bizkranti.dao.BlogDao;
import com.bizkranti.model.Blog;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;



	public String saveBlog(String title, String paragraph, String imageName) {
	    Blog blog = new Blog();
	    blog.setTitle(title);
	    blog.setParagraph(paragraph);
	    blog.setImageName(imageName); // Automatically store the imageName
	    blogDao.save(blog);

	    return "Blog with image saved successfully";
	}

	
//	public Path loadImage(String imageName) throws IOException {
//        String pathDirectory = new ClassPathResource("static/images/").getFile().getAbsolutePath();
//        return Paths.get(pathDirectory).resolve(imageName).normalize();
//    }
	
	

}
