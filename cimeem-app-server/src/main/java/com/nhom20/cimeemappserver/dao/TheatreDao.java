package com.nhom20.cimeemappserver.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.Cinemas;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Room;
import com.nhom20.cimeemappserver.entity.TheatreShowTimes;
import com.nhom20.cimeemappserver.entity.Theatres;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface TheatreDao extends JpaRepository<Theatres, String>{
	

}
