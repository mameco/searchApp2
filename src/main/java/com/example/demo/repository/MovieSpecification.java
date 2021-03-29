package com.example.demo.repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.thymeleaf.util.StringUtils;

import com.example.demo.model.entity.MovieMain;
import com.example.demo.model.form.MovieSearchForm;
import com.sun.istack.NotNull;


//Specificationで動的クエリ生成
public class MovieSpecification {

	//クエリの生成
	public static Specification<MovieMain> GenerateMovieSpecification(final MovieSearchForm form) {

		return new Specification<MovieMain>() {

			@Override
			//CriteriaBuilderインターフェース→条件クエリ、複合選択、式、述語、順序付けの構築に使用されます。
			//クエリの条件生成してる？一つずつつなげてる
			//Rootは何？root→フォームの値？form→Thymeleafで入力されてとんできた値？
			public Predicate toPredicate(Root<MovieMain> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate condition = null;

				if(form.getId() != null) {

					//IDを条件に追加
					//CriteriaBuilderのequalメソッド
					//→引数が等しいかどうかをテストするための述語を作成します。
					//cbはequalやandメソッドを呼び出すための変数？
					Predicate newCondition = cb.equal(root.get("id"), form.getId());

					condition = getPredicate(cb, condition, newCondition);
				}

				//StringUtilのisEmpty→nullか空文字の場合にtrueを返す。
				//(StringのisEmpty→length()が0の場合のみtrueを返す。)
				if (!StringUtils.isEmpty(form.getMovieTitle())) {

					//タイトルを条件に追加
					form.setMovieTitle(form.getMovieTitle().trim());
					Predicate newCondition = cb.like(root.get("movieTitle"), "%" + form.getMovieTitle() + "%");
					condition = getPredicate(cb, condition, newCondition);
				}

				if (form.getGenreId() != null && form.getGenreId() != 0) {

					//ジャンルを条件に追加
					Predicate newCondition = cb.equal(root.get("genreId"), form.getGenreId());
					condition = getPredicate(cb, condition, newCondition);
				}

				if (form.getPerformerId() != null && form.getPerformerId() != 0) {

					//出演者を条件に追加
					Predicate newCondition = cb.equal(root.get("performerId"), form.getPerformerId());
					condition = getPredicate(cb, condition, newCondition);
				}

				return condition;
			}

			//検索条件結合
			public Predicate getPredicate(CriteriaBuilder cb, Predicate condition, @NotNull Predicate newCondition) {

				//もともと条件がある場合andでつなげる
				if (condition != null) {
					condition = cb.and(condition, newCondition);
				} else {
					//なければ先頭の条件になる
					condition = newCondition;
				}

				return condition;

			}
		};
	}
}