package com.example.demo.model.entity;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name = "genre")
public class Genre {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "genre")
	private String genre;

	@Column(name = "del_flg")
	private int delFlg;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "update_date")
	private Timestamp updateDate;


	//外部キー設定
	@Transient
	@OneToMany(targetEntity = MovieMain.class, mappedBy = "genre")
	@JoinColumn(name = "id", referencedColumnName = "genre_id")
	private List<MovieMain> movieMain;

}