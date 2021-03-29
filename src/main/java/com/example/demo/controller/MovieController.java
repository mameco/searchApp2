package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.MovieMain;
import com.example.demo.model.entity.Performer;
import com.example.demo.model.form.MovieSearchForm;
import com.example.demo.service.MovieService;

@Controller
@RequestMapping(value = "/movie")
public class MovieController {

	//フィールド
	private final MovieService movieService;

	//コンストラクタ
	@Autowired //＠Autowiredで指定したクラスがインターフェイスの場合、それを実装しているクラスを自動で注入してくれる
	public MovieController(MovieService service) {
		this.movieService = service;
	}

	//ジャンルテーブルのエンティティのリストを取得する
	//@ModelAttribute メソッドについている場合
	//→Springがハンドラメソッドを呼ぶ前に、@ModelAttributeつきのメソッドを呼び出し、その戻り値をModelに詰める。
	@ModelAttribute //http://www.ifelse.jp/blog/spring-boot-01
	public List<Genre> getListGenre() {

		return movieService.getListGenre();
	}

	//出演者テーブルのエンティティのリストを取得する
	@ModelAttribute
	public List<Performer> getListPerformer() {

		return movieService.getListPerformer();
	}


	//検索画面に遷移する
	//@ModelAttribute 引数についている場合
	//→Spring がハンドラメソッドを呼ぶ前に、対応するオブジェクトをModelから探し、ハンドラメソッドのオブジェクトとして渡される
	//（getListGenreメソッドとgetListPerformerメソッドを呼び出してる？）
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showSearchMovie(@ModelAttribute final MovieSearchForm form) {

		return "movie/search";
	}


	//検索結果を取得して検索画面に遷移する
	//Model→htmlへ値を渡す場合は、Modelを使用する。メソッドの引数でModelを定義しておき、そのModelに対して値をセットする
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String searchMovie(final MovieSearchForm form, final Model model) {

		List<MovieMain> list = movieService.getListMovieMain(form);
		if (list != null) {
			model.addAttribute(list);
//			model.addAttribute("url", "search");  ←なんの為？いる？（ページング用なら不要）
		}

		return "movie/search";
	}




}
