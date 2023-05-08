package com.nhom20.cimeemappserver.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Movie;
import com.nhom20.cimeemappserver.entity.ShowTimings;
import com.nhom20.cimeemappserver.entity.TheatreShowTimes;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;

public interface TheatreShowTimeDao extends JpaRepository<TheatreShowTimes, String> {

	

//SELECT  movie.movie_id, movie.title
//FROM     movie INNER JOIN
//                  theatre_show_times ON movie.movie_id = theatre_show_times.movie_id
//GROUP BY  movie.movie_id, movie.title
	@Query(value = "\r\n" + "SELECT  movie.movie_id, movie.title\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "GROUP BY  movie.movie_id, movie.title", nativeQuery = true)
	public List<Object[]> getListTheatreShow();

//SELECT theatres.theatre_id, theatres.theatre_name
//FROM     theatres INNER JOIN
//                  theatre_show_times ON theatres.theatre_id = theatre_show_times.theatre_id
//where  [dbo].[theatre_show_times].[movie_id] = '66638e01-dfdd-45cb-b511-39d300815f4b'
//GROUP BY theatres.theatre_id, theatres.theatre_name
	@Query(value = "\r\n" + "SELECT theatres.theatre_id, theatres.theatre_name\r\n" + "FROM     theatres INNER JOIN\r\n"
			+ "                  theatre_show_times ON theatres.theatre_id = theatre_show_times.theatre_id\r\n"
			+ "where  [dbo].[theatre_show_times].[movie_id] =:id\r\n"
			+ "GROUP BY theatres.theatre_id, theatres.theatre_name", nativeQuery = true)
	public List<Object[]> getListTheatre(@Param(value = "id") String id);

//	SELECT date_starts_from
//	FROM     theatre_show_times
//	where  [dbo].[theatre_show_times].theatre_id = '824d1fcf-0867-4c04-a2da-0032b8a05f1c'
//	GROUP BY date_starts_from
	@Query(value = "\r\n" + "SELECT date_starts_from,room_id\r\n" + "FROM     theatre_show_times\r\n"
			+ "where  [dbo].[theatre_show_times].theatre_id =:id and movie_id=:movieId\r\n"
			+ "GROUP BY date_starts_from,room_id", nativeQuery = true)
	public List<Object[]> getListTime(@Param(value = "id") String id, @Param(value = "movieId") String movieId);

//select * from [dbo].[theatre_show_times]
//where  [dbo].[theatre_show_times].[movie_id] = '26ad1864-f77e-4a11-aff0-6f394ab162bb'
	@Query(value = "\r\n" + "select * from [dbo].[theatre_show_times]\r\n"
			+ "where  [dbo].[theatre_show_times].[movie_id] =:id", nativeQuery = true)
	public List<TheatreShowTimes> getListTheatreByMovie(@Param(value = "id") String id);

//	select * from [dbo].[theatre_show_times]
//			where  [dbo].[theatre_show_times].[date_starts_from]='2023-01-19'
	@Query(value = "select * from [dbo].[theatre_show_times]\r\n"
			+ "where  [dbo].[theatre_show_times].[date_starts_from]=:date", nativeQuery = true)
	public List<TheatreShowTimes> getListTimeByTheatre(@Param(value = "date") String date);

//SELECT movie.movie_id, movie.title
//FROM     movie INNER JOIN
//                  theatre_show_times ON movie.movie_id = theatre_show_times.movie_id
//where  [dbo].[theatre_show_times].[date_starts_from]='2023-01-23' and  theatre_show_times.theatre_id='824D1FCF-0867-4C04-A2DA-0032B8A05F1C'
//GROUP BY movie.movie_id, movie.title
	@Query(value = "\r\n" + "SELECT movie.movie_id, movie.title\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "where  [dbo].[theatre_show_times].[date_starts_from]=:date and theatre_show_times.theatre_id=:id\r\n"
			+ "GROUP BY movie.movie_id, movie.title", nativeQuery = true)
	public List<Object[]> getListMovieByDayandTheatre(@Param(value = "date") String date,
			@Param(value = "id") String id);

//SELECT room.room_id,room.room_name
//
//FROM     room INNER JOIN
//                  theatre_show_times ON room.room_id = theatre_show_times.room_id
//where  [dbo].[theatre_show_times].[date_starts_from]='2023-01-23' 
//and  theatre_show_times.theatre_id='824D1FCF-0867-4C04-A2DA-0032B8A05F1C'
//and  theatre_show_times.movie_id='26AD1864-F77E-4A11-AFF0-6F394AB162BB'
	@Query(value = "SELECT show_timings.time, show_timings.show_time_id, room.room_name, room.room_id\r\n"
			+ "FROM     theatre_show_times INNER JOIN\r\n"
			+ "                  room ON theatre_show_times.room_id = room.room_id INNER JOIN\r\n"
			+ "                  show_timings ON theatre_show_times.show_time_id = show_timings.show_time_id\r\n"
			+ "where  [dbo].[theatre_show_times].[date_starts_from]=:date \r\n"
			+ "and  theatre_show_times.theatre_id=:theatreId\r\n" + "and  theatre_show_times.movie_id=:movieId\r\n"
			+ "GROUP BY show_timings.time, show_timings.show_time_id, room.room_name, room.room_id", nativeQuery = true)
	public List<Object[]> getListRoomByDayandTheatre(@Param(value = "date") String date,
			@Param(value = "theatreId") String theatreId, @Param(value = "movieId") String movieId);

//SELECT movie.movie_id, movie.title
//FROM     movie INNER JOIN
//                  theatre_show_times ON movie.movie_id = theatre_show_times.movie_id
//where    theatre_show_times.theatre_id='824D1FCF-0867-4C04-A2DA-0032B8A05F1C'
//GROUP BY movie.movie_id, movie.title
	@Query(value = "\r\n" + "SELECT movie.movie_id, movie.title\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "where    theatre_show_times.theatre_id=:theatreId\r\n"
			+ "GROUP BY movie.movie_id, movie.title", nativeQuery = true)
	public List<Object[]> getListMovieByTheatre(@Param(value = "theatreId") String theatreId);

//	SELECT date_starts_from
//	FROM     theatre_show_times
//	where [theatre_id]='824d1fcf-0867-4c04-a2da-0032b8a05f1c' and movie_id='66638e01-dfdd-45cb-b511-39d300815f4b'
	@Query(value = "SELECT date_starts_from\r\n" + "FROM     theatre_show_times\r\n"
			+ "where [theatre_id]=:theatreId and movie_id=:movieId", nativeQuery = true)
	public List<Object[]> getListDayByTheatre(@Param(value = "theatreId") String theatreId,
			@Param(value = "movieId") String movieId);

//	SELECT room.room_id, room.room_name
//	FROM     room INNER JOIN
//	                  theatre_show_times ON room.room_id = theatre_show_times.room_id
//	where [theatre_id]='824d1fcf-0867-4c04-a2da-0032b8a05f1c' and movie_id='66638e01-dfdd-45cb-b511-39d300815f4b' and date_starts_from='2023-01-19'
//	GROUP BY room.room_id, room.room_name
	@Query(value = "SELECT show_timings.time, show_timings.show_time_id, room.room_name, room.room_id\r\n"
			+ "FROM     theatre_show_times INNER JOIN\r\n"
			+ "                  room ON theatre_show_times.room_id = room.room_id INNER JOIN\r\n"
			+ "                  show_timings ON theatre_show_times.show_time_id = show_timings.show_time_id\r\n"
			+ "where [theatre_id]=:theatreId and movie_id=:movieId and date_starts_from=:day\r\n"
			+ "GROUP BY show_timings.time, show_timings.show_time_id, room.room_name, room.room_id", nativeQuery = true)
	public List<Object[]> getListRoomByTheatreAndDay(@Param(value = "theatreId") String theatreId,
			@Param(value = "movieId") String movieId, @Param(value = "day") String day);

//show movie cho nay luon	
	@Query(value = "SELECT theatre_show_times.*\r\n" + "FROM     theatre_show_times INNER JOIN\r\n"
			+ "                  room ON theatre_show_times.room_id = room.room_id INNER JOIN\r\n"
			+ "                  show_timings ON theatre_show_times.show_time_id = show_timings.show_time_id\r\n"
			+ "where [theatre_id]=:theatreId "
			+ "and movie_id=:movieId "
			+ "and date_starts_from=:day "
			+ "and show_timings.show_time_id=:timeId "
			+ "and room.room_id=:roomId\r\n", nativeQuery = true)
	public List<TheatreShowTimes> getRoomByAllSort(@Param(value = "theatreId") String theatreId,
			@Param(value = "movieId") String movieId, @Param(value = "day") String day,
			@Param(value = "timeId") String timeId, @Param(value = "roomId") int roomId);
//	select * from [dbo].[theatre_show_times] where [date_starts_from]='2023-03-02' and [movie_id]='6f78b1b7-9ef8-42ba-bb2c-7c0567b14324'
	@Query(value = "select * from [dbo].[theatre_show_times] where [date_starts_from]=:day and [movie_id]=:movieId", nativeQuery = true)
	public List<TheatreShowTimes> getTheatreByMovieAndDay(
			@Param(value = "movieId") String movieId, @Param(value = "day") String day);

}
