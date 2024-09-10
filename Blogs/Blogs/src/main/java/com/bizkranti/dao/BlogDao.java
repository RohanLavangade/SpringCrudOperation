package com.bizkranti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizkranti.model.Blog;

@Repository
public interface BlogDao extends JpaRepository<Blog, Long>{

}
