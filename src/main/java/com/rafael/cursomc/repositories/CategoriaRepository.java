package com.rafael.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.cursomc.domain.Categorias;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Integer>{

	Page<Categorias> findAll(Pageable pageRequest);
}
