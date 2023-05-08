package com.nhom20.cimeemappserver.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface LocationDao extends JpaRepository<Location, String>{

	

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO [dbo].[location_movie]  ([location_id],[movie_id])\r\n"
			+ "VALUES (:actor,:bookId )",nativeQuery = true)
	void saveLocAndBook(@Param(value = "actor") String actor,@Param(value = "bookId") String bookId);
}
