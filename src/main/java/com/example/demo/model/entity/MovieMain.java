package com.example.demo.model.entity;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table(name="movie_main")
public class MovieMain {

	@Id //主キーにつける
	@GenericGenerator(name = "gen", strategy = "increment") //何？
	@GeneratedValue(generator = "gen") //プライマリキーカラムにユニークな値を自動で生成するアノテーション
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name ="image")
	private String image;

	@Column(name = "performer_id")
	private int performerId;

	@Column(name = "genre_id")
	private int genreId;

	@Column(name = "del_flg")
	private int delFlg;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "update_date")
	private Timestamp updateDate;

	//外部キー設定
	@ManyToOne(targetEntity = Performer.class) //targetEntity→関連付けの対象となるエンティティクラス
	@JoinColumn(name = "performer_id", referencedColumnName = "id", insertable = false, updatable = false)
	//↑inserttable属性は、@JoinColumnで指定したカラムをSQLのINSERT文に含むかどうか指定する属性
	private Performer performer;

}