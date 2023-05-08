package com.nhom20.cimeemappserver.dao;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Genres;


public interface GenresDao extends JpaRepositoryImplementation<Genres, String>{

	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO [dbo].[movie_genres]  ([genres_id],[movie_id])\r\n"
			+ "VALUES (:percentage,:bookId )",nativeQuery = true)
	void saveGenAndMovie(@Param(value = "percentage") String percentage,@Param(value = "bookId") String bookId);

}
