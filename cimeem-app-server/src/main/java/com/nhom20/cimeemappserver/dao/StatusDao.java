package com.nhom20.cimeemappserver.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.nhom20.cimeemappserver.entity.Status;

public interface StatusDao extends JpaRepositoryImplementation<Status, String>{
//	update [dbo].[status] set name='' where [status_id]=:statusId
	@Modifying
	@Transactional
	@Query(value = "update [dbo].[status] set [dbo].[status].[status]='CHECKED' where [status_id]=:statusId",nativeQuery = true)
	public void confirm(@Param(value = "statusId")  String id);
	@Modifying
	@Transactional
	@Query(value = "update [dbo].[status] set [dbo].[status].[status]='CANCEL' where [status_id]=:statusId",nativeQuery = true)
	public void cancel(@Param(value = "statusId")  String id);


}
