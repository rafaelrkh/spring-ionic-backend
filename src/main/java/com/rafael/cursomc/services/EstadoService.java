package com.rafael.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.cursomc.domain.Estados;
import com.rafael.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository rep;
	
	public List<Estados> findAll(){
		return rep.findAllByOrderByDsEstado();
	}
}
