package com.rafael.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.cursomc.domain.Cidades;
import com.rafael.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository rep;
	
	public List<Cidades> findByEstado(Integer cdEstado){
		return rep.findCidades(cdEstado);
	}
}
