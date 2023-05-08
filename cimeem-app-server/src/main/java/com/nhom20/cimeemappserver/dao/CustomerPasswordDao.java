package com.nhom20.cimeemappserver.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.ForgotPassword;



public interface CustomerPasswordDao extends JpaRepositoryImplementation<CustomerPassword, String>{
	@Query(value = "select *from customer_password where user_id=:id",nativeQuery = true)
	public CustomerPassword getPassByUserId(@Param(value = "id") String id);

}
