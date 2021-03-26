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
@Table(name="performer")
public class Performer {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "del_flg")
	private int delFlg;


	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "update_date")
	private Timestamp updateDate;

	@Transient //データの永続化を回避する（データベースに入れないということ？）
	@OneToMany(targetEntity = MovieMain.class, mappedBy = "performer")
	//↑ mappedBy→所有者側のエンティティクラス(MovieMain)で関係を保持しているフィールドの名前を指定する
	@JoinColumn(name = "id", referencedColumnName = "performer_id")
	private List<MovieMain> movieMainList;

}
