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
import com.nhom20.cimeemappserver.entity.TicketRate;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface TicketRateDao extends JpaRepository<TicketRate, String>{
	@Query(value = "SELECT  ticket_rate.*\r\n"
			+ "FROM     price INNER JOIN\r\n"
			+ "                  ticket_rate_price ON price.price_id = ticket_rate_price.price_id INNER JOIN\r\n"
			+ "                  ticket_rate ON ticket_rate_price.ticket_rate_id = ticket_rate.ticket_rate_id INNER JOIN\r\n"
			+ "                  seat_type ON ticket_rate.seat_id = seat_type.seat_type_id\r\n"
			+ "	where price.price_id=:id",nativeQuery = true)
	List<TicketRate> listTicketRate(@Param(value = "id")String id);

	
}
