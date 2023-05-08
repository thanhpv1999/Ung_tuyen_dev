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
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Roles;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface RolesDao extends JpaRepository<Roles, String>{
	@Modifying
	@Transactional
	@Query(value = "UPDATE [dbo].[role_employee]\r\n"
			+ "		SET [role_id]=:gen WHERE [employee_id]=:id",nativeQuery = true)
	public void updateRoleEmployee(@Param(value = "gen") String gen,@Param(value = "id") String id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE [dbo].[role_user]\r\n"
			+ "		SET [role_id]=:gen WHERE [user_id]=:id",nativeQuery = true)
	public void updateRoleUser(@Param(value = "gen") String gen,@Param(value = "id") String id);
	
}
