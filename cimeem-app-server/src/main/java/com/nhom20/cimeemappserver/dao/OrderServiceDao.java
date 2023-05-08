package com.nhom20.cimeemappserver.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Order;
import com.nhom20.cimeemappserver.entity.OrderDetail;
import com.nhom20.cimeemappserver.entity.OrderService;

import javax.transaction.Transactional;

public interface OrderServiceDao extends JpaRepositoryImplementation<OrderService, String>{

}
