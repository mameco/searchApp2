package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.MovieMain;
import com.example.demo.model.entity.Performer;
import com.example.demo.model.form.MovieSearchForm;

public interface MovieService {

	List<Genre> getListGenre();

	List<Performer> getListPerformer();

	List<MovieMain> getListMovieMain(final MovieSearchForm form);

	Optional<MovieMain> getMovieMain(final long id);
}
