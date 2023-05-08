package com.nhom20.cimeemappserver.dao;

import java.sql.Date;
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
import com.nhom20.cimeemappserver.entity.SeatNo;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface SeatNoDao extends JpaRepository<SeatNo, Integer>{
	
	@Query(value = "SELECT seat_no.requested_time\r\n"
			+ "FROM     seat INNER JOIN\r\n"
			+ "                  seat_no ON seat.seat_id = seat_no.seat_id INNER JOIN\r\n"
			+ "                  room ON seat.room_id = room.room_id\r\n"
			+ "where seat.row_no=:idNo and [seat_no].[seat_no]=:idSeat and room.room_name=:name \r\n"
			+ "GROUP BY seat_no.seat_id, seat_no.seat_no, seat_no.requested_time, seat_no.seat_type_id,seat.row_no,seat_no.status,seat.[seat_id]", nativeQuery = true)
	public List<java.util.Date> setTimeForSeat(@Param(value = "idNo") String idNo,@Param(value = "idSeat") int name,@Param(value = "name") String idSeat);
	@Query(value = "SELECT seat.[seat_id]\r\n"
			+ "FROM     seat INNER JOIN\r\n"
			+ "                  seat_no ON seat.seat_id = seat_no.seat_id INNER JOIN\r\n"
			+ "                  room ON seat.room_id = room.room_id\r\n"
			+ "where seat.row_no=:idNo and [seat_no].[seat_no]=:idSeat and room.room_name=:name \r\n"
			+ "GROUP BY seat_no.seat_id, seat_no.seat_no, seat_no.requested_time, seat_no.seat_type_id,seat.row_no,seat_no.status,seat.[seat_id]", nativeQuery = true)
	public List<Integer> getSeat(@Param(value = "idNo") String idNo,@Param(value = "idSeat") int name,@Param(value = "name") String idSeat);
	@Modifying
	@Transactional
	@Query(value = "\r\n"
			+ "UPDATE [dbo].[seat_no]\r\n"
			+ "		SET [requested_time] =CONVERT(datetime, SWITCHOFFSET(CONVERT(datetimeoffset,  GETUTCDATE()), '+07:00'))\r\n"
			+ "		 where [seat_no]=:idNo and [seat_id]=:idSeat",nativeQuery = true)
	public void updateTimeForSeat(@Param(value = "idNo" )int idNo,@Param(value = "idSeat" )int idSeat);

	
	@Query(value = "select * from  [dbo].[seat_no] where [seat_id]=:idNo and [seat_no]=:idSeat",nativeQuery = true)
	public List<SeatNo> getSeatNo(@Param(value = "idNo" )int idNo,@Param(value = "idSeat" )int idSeat);
	
//	SELECT price.value
//	FROM     seat_no INNER JOIN
//	                  ticket_rate INNER JOIN
//	                  seat_type ON ticket_rate.seat_id = seat_type.seat_type_id INNER JOIN
//	                  ticket_rate_price ON ticket_rate.ticket_rate_id = ticket_rate_price.ticket_rate_id INNER JOIN
//	                  price ON ticket_rate_price.price_id = price.price_id ON seat_no.seat_type_id = seat_type.seat_type_id
//	where  seat_no.seat_id=1 and seat_no.seat_no=1
	@Query(value = "SELECT  ticket_rate.ticket_rate_id,price.value\r\n"
			+ "FROM     seat_no INNER JOIN\r\n"
			+ "                  ticket_rate INNER JOIN\r\n"
			+ "                  seat_type ON ticket_rate.seat_id = seat_type.seat_type_id INNER JOIN\r\n"
			+ "                  ticket_rate_price ON ticket_rate.ticket_rate_id = ticket_rate_price.ticket_rate_id INNER JOIN\r\n"
			+ "                  price ON ticket_rate_price.price_id = price.price_id ON seat_no.seat_type_id = seat_type.seat_type_id\r\n"
			+ "where  seat_no.seat_id=:idSeat and seat_no.seat_no=:idNo\r\n"
			+ "",nativeQuery = true)
	public List<Object[]> getPrice(@Param(value = "idNo" )int idNo,@Param(value = "idSeat" )int idSeat);

	
}
