package com.nhom20.cimeemappserver.dao;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Room;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

public interface RoomDao extends JpaRepository<Room, String> {

	String empNumber = "a";
	static final String generatedString = RandomStringUtils.randomAlphanumeric(10);

	@Modifying
	@Transactional
	@Query(value = "CREATE TABLE ##" + empNumber
			+ " (screen_name varchar(max), screen_rows varchar(1), Seats varchar(2), selected_seats numeric (10));\r\n"
			+ "with List as (\r\n" + "\r\n"
			+ "SELECT a.row_no, ROW_NUMBER() OVER (partition BY a.seat_id order by a.seat_id) AS 'Seats', a.room_id, a.seat_id\r\n"
			+ "\r\n" + "FROM dbo.seat a join dbo.seat_no b on a.seat_id = b.seat_id\r\n" + "\r\n"
			+ "where a.room_id =:idRoom\r\n" + "\r\n" + ")\r\n" + "\r\n" + "insert into ##" + empNumber + "\r\n"
			+ "select d.room_name screen_name, l.row_no screen_ows, l.Seats,\r\n" + "\r\n"
			+ "case when c.status = 'true' then c.seat_no else 0 end selected_seats\r\n"
			+ "from List l left join seat_no c\r\n" + "\r\n" + "on l.seat_id = c.seat_id and l.Seats = c.seat_no\r\n"
			+ "\r\n" + "left join room d\r\n" + "\r\n" + "on l.room_id= d.room_id\r\n" + "\r\n"
			+ "declare @alias_period_list as varchar(max)\r\n" + "declare @period_list as varchar(max)\r\n"
			+ "declare @dynamic_pivot_query as varchar(max)\r\n"
			+ "select @alias_period_list = stuff((select distinct ',['+Seats+']' from ##" + empNumber
			+ " for xml path('')),1,1,'')\r\n" + "select @period_list = stuff((select distinct ',['+Seats+']' from ##"
			+ empNumber + " for xml path('')),1,1,'')\r\n" + "set @dynamic_pivot_query =\r\n"
			+ "'select [screen_name], [screen_rows], '+@alias_period_list+'from (\r\n"
			+ "SELECT [screen_name], [screen_rows], [Seats], [selected_seats] FROM ##" + empNumber + ") as S\r\n"
			+ "Pivot (SUM([selected_seats]) FOR [Seats] IN ('+@period_list+')) as P'\r\n"
			+ "exec (@dynamic_pivot_query)\r\n" + "", nativeQuery = true)
	public void getTableRoom(@Param(value = "idRoom")int idRoom);
	
	@Query(value = "select * from ##a",nativeQuery = true)
	public List<Object[]> listTable();
	
	@Modifying
	@Transactional
	@Query(value = "drop table ##a",nativeQuery = true)
	public void dropTable();
	@Query(value = "select * from [dbo].[room] where [cinema_id]=:name ",nativeQuery = true)
	public List<Room> listRoomByCinema(@Param(value = "name")String name);

}
