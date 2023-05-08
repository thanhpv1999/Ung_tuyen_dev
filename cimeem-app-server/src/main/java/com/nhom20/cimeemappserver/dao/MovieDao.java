package com.nhom20.cimeemappserver.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Movie;

public interface MovieDao extends JpaRepository<Movie, String> {
	@Query(value = "SELECT movie.*\r\n"
			+ "FROM     movie INNER JOIN\r\n"
			+ "                  theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "where ([date_starts_from]= GETDATE() or GETDATE() >[date_starts_from]) and (GETDATE()< [date_ends] or [date_ends]= GETDATE())\r\n"
			+ "GROUP BY movie.movie_id, movie.title, movie.description, movie.duration_min, movie.rate_id, movie.path_img, movie.url_trailer, movie.start_up_date, movie.language_id, movie.views, movie.votes_avg, movie.votes_count, movie.date_aired,movie.active\r\n"
			+ "", nativeQuery = true)
	public List<Movie> getListTheatreShowTimes();

//	select * from [dbo].[theatre_show_times] where ([date_starts_from]= GETDATE() or GETDATE() <[date_starts_from])and (GETDATE()< [date_ends] or [date_ends]= GETDATE())
	@Query(value = "SELECT movie.*\r\n"
			+ "FROM     movie INNER JOIN\r\n"
			+ "                  theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "where ([date_starts_from]= GETDATE() or GETDATE() <[date_starts_from])and (GETDATE()< [date_ends] or [date_ends]= GETDATE())\r\n"
			+ "GROUP BY movie.movie_id, movie.title, movie.description, movie.duration_min, movie.rate_id, movie.path_img, movie.url_trailer, movie.start_up_date, movie.language_id, movie.views, movie.votes_avg, movie.votes_count, movie.date_aired,movie.active", nativeQuery = true)
	public List<Movie> getListTheatreShowTimesTomorow();
	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id  INNER JOIN\r\n"
			+ "	    theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "						 where movie_genres.genres_id=:genId " + "and movie.date_aired like %:nam% "
			+ "and ((theatre_show_times.[date_starts_from]= GETDATE() or GETDATE() >theatre_show_times.[date_starts_from])and (GETDATE()< theatre_show_times.[date_ends] or theatre_show_times.[date_ends]= GETDATE()))", nativeQuery = true)
	public Page<Movie> listMoveByGenId1(@Param(value = "genId") String genId, @Param(value = "nam") String nam,
			Pageable pageable);

	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id  INNER JOIN\r\n"
			+ "	    theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "						 where movie.date_aired like %:nam% "
			+ "and ((theatre_show_times.[date_starts_from]= GETDATE() or GETDATE() >theatre_show_times.[date_starts_from])and (GETDATE()< theatre_show_times.[date_ends] or theatre_show_times.[date_ends]= GETDATE()))", nativeQuery = true)
	public Page<Movie> listMoveByNamAndNow(@Param(value = "nam") String nam, Pageable pageable);

	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id  INNER JOIN\r\n"
			+ "	    theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "						 where movie_genres.genres_id=:genId " + "and movie.date_aired like %:nam% "
			+ "and (([date_starts_from]= GETDATE() or GETDATE() <[date_starts_from])"
			+ "and (GETDATE()< [date_ends] or [date_ends]= GETDATE()))", nativeQuery = true)
	public Page<Movie> listMoveByGenId2(@Param(value = "genId") String genId, @Param(value = "nam") String nam,
			Pageable pageable);

	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id  INNER JOIN\r\n"
			+ "	    theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "						 where movie.date_aired like %:nam% "
			+ "and (([date_starts_from]= GETDATE() or GETDATE() <[date_starts_from])"
			+ "and (GETDATE()< [date_ends] or [date_ends]= GETDATE()))", nativeQuery = true)
	public Page<Movie> listMoveByNamAndNotNow(@Param(value = "nam") String nam, Pageable pageable);

	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id  INNER JOIN\r\n"
			+ "	    theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "						 where movie_genres.genres_id=:genId "
			+ "and movie.date_aired like %:nam% ", nativeQuery = true)
	public Page<Movie> listMoveByGenId3(@Param(value = "genId") String genId, @Param(value = "nam") String nam,
			Pageable pageable);

	@Query(value = "select * from movie order by views desc", nativeQuery = true)
	public Page<Movie> listMovieOrderByView(Pageable pageable);

	@Query(value = "select * from movie where movie.title like %:name%", nativeQuery = true)
	public Page<Movie> listByName(@Param(value = "name") String name, Pageable pageable);

	@Query(value = "select * from movie order by votes_avg desc", nativeQuery = true)
	public Page<Movie> listMovieOrderByVotesAvg(Pageable pageable);

	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id\r\n"
			+ "						 where movie_genres.genres_id=:genId", nativeQuery = true)
	public Page<Movie> listMoveByGenId(@Param(value = "genId") String genId, Pageable pageable);

	@Query(value = "select * from movie where movie.date_aired like %:nam%", nativeQuery = true)
	public Page<Movie> listByNam(@Param(value = "nam") String nam, Pageable pageable);

	@Query(value = "SELECT  movie.* FROM     movie INNER JOIN\r\n"
			+ "		        theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "		where (theatre_show_times.[date_starts_from]= GETDATE() or GETDATE() >theatre_show_times.[date_starts_from]) and (GETDATE()< theatre_show_times.[date_ends] or theatre_show_times.[date_ends]= GETDATE()) ", nativeQuery = true)
	public Page<Movie> getListTheatreShowTimes(Pageable pageable);

	@Query(value = "SELECT  movie.* FROM     movie INNER JOIN\r\n"
			+ "					        theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "		 where (theatre_show_times.[date_starts_from]= GETDATE() or GETDATE() <theatre_show_times.[date_starts_from])and (GETDATE()< theatre_show_times.[date_ends] or theatre_show_times.[date_ends]= GETDATE()) ", nativeQuery = true)
	public Page<Movie> getListTheatreShowTimesTomorow(Pageable pageable);
	
	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id  INNER JOIN\r\n"
			+ "	    theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "						 where movie.date_aired like %:nam% "
			+ "and ((theatre_show_times.[date_starts_from]= GETDATE() or GETDATE() >theatre_show_times.[date_starts_from])and (GETDATE()< theatre_show_times.[date_ends] or theatre_show_times.[date_ends]= GETDATE())) order by movie.views desc ", nativeQuery = true)
	public Page<Movie> listMoveByNamAndNowAndHot(@Param(value = "nam") String nam, Pageable pageable);
	
	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id  INNER JOIN\r\n"
			+ "	    theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "						 where movie.date_aired like %:nam% "
			+ "and ((theatre_show_times.[date_starts_from]= GETDATE() or GETDATE() >theatre_show_times.[date_starts_from])and (GETDATE()< theatre_show_times.[date_ends] or theatre_show_times.[date_ends]= GETDATE())) order by movie.votes_avg desc ", nativeQuery = true)
	public Page<Movie> listMoveByNamAndNowAndVote(@Param(value = "nam") String nam, Pageable pageable);
	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id  INNER JOIN\r\n"
			+ "	    theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "						 where movie.date_aired like %:nam% "
			+ "and (([date_starts_from]= GETDATE() or GETDATE() <[date_starts_from])"
			+ "and (GETDATE()< [date_ends] or [date_ends]= GETDATE())) order by movie.views desc ", nativeQuery = true)
	public Page<Movie> listMoveByNamAndNotNowAndHot(@Param(value = "nam") String nam, Pageable pageable);
	@Query(value = "SELECT movie.*\r\n" + "FROM     movie INNER JOIN\r\n"
			+ "                  movie_genres ON movie.movie_id = movie_genres.movie_id  INNER JOIN\r\n"
			+ "	    theatre_show_times ON movie.movie_id = theatre_show_times.movie_id\r\n"
			+ "						 where movie.date_aired like %:nam% "
			+ "and (([date_starts_from]= GETDATE() or GETDATE() <[date_starts_from])"
			+ "and (GETDATE()< [date_ends] or [date_ends]= GETDATE())) order by movie.votes_avg desc ", nativeQuery = true)
	public Page<Movie> listMoveByNamAndNotNowAndVote(@Param(value = "nam") String nam, Pageable pageable);
}
