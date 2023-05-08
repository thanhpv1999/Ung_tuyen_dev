package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nhom20.cimeemappserver.entity.Genres;


public interface GenresService {

	public List<Genres> listGenres();

	public Page<Genres> listGenresOnPage(Pageable pageable);

	public void saveGenAndMovie(String values, String movieId);
}
