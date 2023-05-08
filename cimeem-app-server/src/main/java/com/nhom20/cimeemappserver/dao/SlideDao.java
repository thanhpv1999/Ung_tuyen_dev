package com.nhom20.cimeemappserver.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.nhom20.cimeemappserver.entity.Slides;

public interface SlideDao extends JpaRepositoryImplementation<Slides, String>{

}
