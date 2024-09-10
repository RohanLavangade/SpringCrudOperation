package com.bizkranti.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bizkranti.model.Blog;
import com.bizkranti.service.BlogService;

import org.springframework.util.StringUtils;

@RestController
@CrossOrigin("*")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@PostMapping("/upload")
	public ResponseEntity<String> saveBlog(@RequestParam("title") String title,
			@RequestParam("paragraph") String paragraph, @RequestParam("file") MultipartFile file) throws IOException {

		String pathDirectory = "static/images/";

		try {

			if (file.isEmpty()) {
				return ResponseEntity.badRequest().body("Image file is empty.");
			}

			// Create directory if it doesn't exist
			File directory = new File(pathDirectory);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			String cleanFileName = StringUtils.cleanPath(file.getOriginalFilename());
			File destinationFile = new File(directory + File.separator + cleanFileName);

			Files.copy(file.getInputStream(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			String finalData = blogService.saveBlog(title, paragraph, cleanFileName);

			return ResponseEntity.ok("Image uploaded: " + cleanFileName);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image.");
		}
	}

}
