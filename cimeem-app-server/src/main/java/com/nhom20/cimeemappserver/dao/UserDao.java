package com.nhom20.cimeemappserver.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amazonaws.services.identitymanagement.model.User;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;

public interface UserDao extends JpaRepository<Users, String> {

	@Query(value = "select *from account_users where email=:email", nativeQuery = true)
	public Users getUsersByGmail(@Param(value = "email") String email);

	@Query(value = "select *from account_users where email=:email or [authen_by]=:provide1 or  [authen_by]=:provide2 ", nativeQuery = true)
	public List<Users> getUsersByGmailAndProvide(@Param(value = "email") String email,
			@Param(value = "provide1") String provide, @Param(value = "provide2") String provide2);

//	UPDATE [dbo].[users]
//			SET first_Name = 'thu', last_Name = 'anh', address='', email='porkoe3443334@gmail.com',phone='0932904529'
//						WHERE user_id='07a9d171-88e4-403a-82c3-b2f8a2c4118c'
	@Modifying
	@Transactional
	@Query(value = "UPDATE account_users\r\n" + "SET first_Name =:first_Name, last_Name =:last_Name, email=:email\r\n"
			+ "			WHERE user_id=:id", nativeQuery = true)
	public void updateUser(@Param(value = "first_Name") String firstName, @Param(value = "last_Name") String lastName,
			@Param(value = "email") String email, @Param(value = "id") String id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE account_users\r\n" + "SET avatar =:avatar\r\n"
			+ "			WHERE user_id=:id", nativeQuery = true)
	public void updateAvaUser(@Param(value = "avatar") String avatar, @Param(value = "id") String id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE account_users\r\n" + "SET active =:active\r\n"
			+ "			WHERE email=:email", nativeQuery = true)
	public void updateActiveUser(@Param(value = "active") String active, @Param(value = "email") String email);

}
