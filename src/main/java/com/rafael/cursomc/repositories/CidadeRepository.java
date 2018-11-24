package com.rafael.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.cursomc.domain.Cidades;

@Repository
public interface CidadeRepository extends JpaRepository<Cidades, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cidades obj WHERE obj.estado.cdEstado = :cdEstado ORDER BY obj.dsCidade ")
	public List<Cidades> findCidades(@Param("cdEstado") Integer cdEstado);
}
