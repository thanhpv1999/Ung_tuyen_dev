package com.nhom20.cimeemappserver.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.entity.Menus;



public interface MenusDao extends JpaRepositoryImplementation<Menus, String>{

	@Query(value = ";WITH y AS (\r\n"
			+ "		  -- anchor:\r\n"
			+ "		 SELECT menus_id,name,url,menu_master\r\n"
			+ "		   FROM menus WHERE menu_master =:menuMaster\r\n"
			+ "		  UNION ALL\r\n"
			+ "	  -- recursive:\r\n"
			+ "		 SELECT t.menus_id, t.name, t.url, t.menu_master\r\n"
			+ "			  FROM y INNER JOIN menus AS t\r\n"
			+ "		  ON t.menu_master = y.menus_id\r\n"
			+ "		)\r\n"
			+ "			SELECT y.menus_id, y.name , y.url,y.menu_master FROM y",nativeQuery = true)
	public List<Menus> listMenuMaster(@Param("menuMaster")String replyId);
}
