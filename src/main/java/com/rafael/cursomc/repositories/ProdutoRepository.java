package com.rafael.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.cursomc.domain.Categorias;
import com.rafael.cursomc.domain.Produtos;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Integer> {

	/*
	@Query("SELECT DISTINCT obj FROM Produtos obj INNER JOIN obj.categorias cat"
			+ " WHERE obj.dsProduto LIKE %:dsProduto% AND cat IN :categorias")
			
	Page<Produtos> search(@Param("dsProduto") String dsProduto,@Param("categorias") List<Categorias> categorias, Pageable pageRequest);
	*/
	@Transactional(readOnly=true)
	Page<Produtos> findDistinctBydsProdutoContainingAndCategoriasIn(String dsProduto, List<Categorias> categorias, Pageable pageRequest);
}
