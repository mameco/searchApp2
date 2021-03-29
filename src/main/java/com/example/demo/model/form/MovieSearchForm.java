package com.example.demo.model.form;
import com.example.demo.model.entity.MovieMain;

import lombok.Data;


@Data
public class MovieSearchForm {

	private Integer id;

	private String movieTitle;

	private Integer performerId;

	private Integer genreId;

	private int isDelete;


	public void setValues(MovieMain movieMain) {
		this.setId((int)movieMain.getId());
		this.setMovieTitle(movieMain.getTitle());
		this.setPerformerId(movieMain.getPerformerId());
		this.setGenreId(movieMain.getGenreId());
		this.setIsDelete(movieMain.getDelFlg());
	}


}