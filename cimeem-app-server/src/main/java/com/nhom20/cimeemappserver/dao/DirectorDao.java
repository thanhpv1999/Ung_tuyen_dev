package com.nhom20.cimeemappserver.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.Director;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface DirectorDao extends JpaRepository<Director, String>{

	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO [dbo].[movie_director]  ([director_id],[movie_id])\r\n"
			+ "VALUES (:actor,:bookId )",nativeQuery = true)
	void saveDirAndBook(@Param(value = "actor") String actor,@Param(value = "bookId") String bookId);
	
}
