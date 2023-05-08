package com.nhom20.cimeemappserver.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Roles;

public interface RolesService {

	public List<Roles> listRoles();

	public void updateRoleEmployee(String gen, String id);

	public void updateRoleUser(String gen, String id);
}
