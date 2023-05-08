package com.nhom20.cimeemappserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.MemberUser;

public interface MemberUsersDao extends JpaRepository<MemberUser, String>{

}
