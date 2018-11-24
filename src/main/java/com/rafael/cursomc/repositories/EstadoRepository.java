package com.rafael.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.rafael.cursomc.domain.Estados;

@Repository
public interface EstadoRepository extends JpaRepository<Estados, Integer>{

	@Transactional(readOnly=true)
	public List<Estados> findAllByOrderByDsEstado();
}
