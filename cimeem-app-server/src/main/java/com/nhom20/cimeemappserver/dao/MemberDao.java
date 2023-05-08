package com.nhom20.cimeemappserver.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amazonaws.services.identitymanagement.model.User;
import com.nhom20.cimeemappserver.entity.Member;
import com.nhom20.cimeemappserver.entity.MemberUser;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;

public interface MemberDao extends JpaRepository<Member, String> {

	

}
