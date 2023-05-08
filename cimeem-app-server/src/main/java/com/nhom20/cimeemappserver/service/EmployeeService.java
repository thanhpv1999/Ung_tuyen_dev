package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Employee;
import com.nhom20.cimeemappserver.entity.Menus;


public interface EmployeeService {

	public Page<Employee> listEmployee(Pageable pageable);
}
