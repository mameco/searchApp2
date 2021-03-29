package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.MovieMain;
import com.example.demo.model.entity.Performer;
import com.example.demo.model.form.MovieSearchForm;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.MovieMainRepository;
import com.example.demo.repository.MovieSpecification;
import com.example.demo.repository.PerformerRepository;


@Service
public class MovieServiceImpl implements MovieService {

	//フィールド
	private final MovieMainRepository movieMainRepository;

	private final GenreRepository genreRepository;

	private final PerformerRepository performerRepository;


	//コンストラクタ
	public MovieServiceImpl(MovieMainRepository main, GenreRepository genre, PerformerRepository performer) {
		this.movieMainRepository = main;
		this.genreRepository = genre;
		this.performerRepository = performer;
	}

	//ジャンルテーブルのレコードをID順に取得する
	public List<Genre> getListGenre() {

		List<Genre> list = genreRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

		return list;
	}

	//出演者テーブルのレコードをID順に取得する
	public List<Performer> getListPerformer() {

		List<Performer> list = performerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

		return list;
	}

	//検索条件を生成しメインテーブルのレコードを取得する
	public List<MovieMain> getListMovieMain(final MovieSearchForm form) {

		//リポジトリのGenerateMovieSpecificationメソッドを呼び出して条件生成する
		Specification<MovieMain> specification = MovieSpecification.GenerateMovieSpecification(form);

		//↑で生成した条件（specification）でレコードを取得
		List<MovieMain> list = movieMainRepository.findAll(specification, Sort.by(Sort.Direction.ASC, "id"));

		return list;
	}

	//メインテーブルを主キー検索する
	public Optional<MovieMain> getMovieMain(final long id) {

		//バージョン2.0からこの書き方らしい
		//Optionalは、値があるかもしれないし無い（null）かもしれない ことを表現するための型
		Optional<MovieMain> movieMain = movieMainRepository.findById(id);

		return movieMain;
	}

}
