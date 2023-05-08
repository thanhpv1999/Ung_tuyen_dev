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
import com.nhom20.cimeemappserver.entity.ShowTimings;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface ShowTimeDao extends JpaRepository<ShowTimings, String>{
//	SELECT time
//	FROM    show_timings
//	WHERE  show_time_id NOT IN(select show_time_id from  
//	                  theatre_show_times 
//					  where [room_id]=3 and [theatre_id]='7929abf8-4fe2-4ade-a86e-02d750589bab')
	@Query(value = "SELECT *\r\n"
			+ "FROM    show_timings\r\n"
			+ "WHERE  show_time_id NOT IN(select show_time_id from  \r\n"
			+ "                  theatre_show_times \r\n"
			+ "				  where [room_id]=:roomId and [theatre_id]=:theatreId)", nativeQuery = true)
	public List<ShowTimings> getTimeByIdRoomAndIdThre(@Param(value = "theatreId") String theatreId, @Param(value = "roomId") int roomId);
	
}
