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
import com.nhom20.cimeemappserver.entity.SeatType;
import com.nhom20.cimeemappserver.entity.Users;

import lombok.val;

import javax.transaction.Transactional;


public interface SeatTypeDao extends JpaRepository<SeatType, Integer>{
	@Query(value = "SELECT  seat_type.*\r\n"
			+ "FROM     seat_type INNER JOIN\r\n"
			+ "                  ticket_rate ON seat_type.seat_type_id = ticket_rate.seat_id\r\n"
			+ "	where ticket_rate.ticket_rate_id=:id",nativeQuery = true)
	List<SeatType> listSeatType(@Param(value = "id") String id);
//	SELECT seat_type.[seat_arrangement_at]
//	FROM     seat INNER JOIN
//	                  seat_no ON seat.seat_id = seat_no.seat_id INNER JOIN
//	                  room ON seat.room_id = room.room_id
//			 INNER JOIN
//	                  seat_type ON seat_no.seat_type_id = seat_type.seat_type_id
//	where seat.row_no='B' and [seat_no].seat_no=1 and room.room_name='Cinema 6' 
//	GROUP BY seat_no.seat_id, seat_no.seat_no, seat_no.requested_time, seat_no.seat_type_id,seat.row_no,seat_no.status,seat.[seat_id],seat_type.[seat_arrangement_at]
@Query(value = "SELECT seat_type.seat_arrangement_at\r\n"
		+ "FROM     seat INNER JOIN\r\n"
		+ "                  seat_no ON seat.seat_id = seat_no.seat_id INNER JOIN\r\n"
		+ "                  room ON seat.room_id = room.room_id\r\n"
		+ "		 INNER JOIN\r\n"
		+ "                  seat_type ON seat_no.seat_type_id = seat_type.seat_type_id\r\n"
		+ " where seat.row_no=:idNo and seat_no.seat_no=:idSeat and room.room_name=:name \r\n"
		
		+ "",nativeQuery = true)
public List<String> listType(@Param(value = "idNo") String idNo,@Param(value = "idSeat") int name,@Param(value = "name") String idSeat);

	
}
