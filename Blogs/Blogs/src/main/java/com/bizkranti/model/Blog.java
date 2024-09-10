package com.bizkranti.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;

@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY or SEQUENCE based on your DB
	private Long id;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String paragraph;

	
	private String ImageName;

	@Column(name = "created_date", nullable = false, updatable = false)
	private LocalDate createdDate;

	@PrePersist
	protected void onCreate() {
		createdDate = LocalDate.now();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	public String getImageName() {
		return ImageName;
	}

	public void setImageName(String imageName) {
		ImageName = imageName;
	}

	public Blog(String title, String paragraph, String imageName) {
		super();
		this.title = title;
		this.paragraph = paragraph;
		ImageName = imageName;
	}

	public Blog() {
	}

}
