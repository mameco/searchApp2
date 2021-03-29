package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.entity.MovieMain;


//メインリポジトリインターフェース
//JpaRepository<エンティティ, IDタイプのラッパークラス>
public interface MovieMainRepository extends JpaRepository<MovieMain, Long>, JpaSpecificationExecutor<MovieMain> {

}