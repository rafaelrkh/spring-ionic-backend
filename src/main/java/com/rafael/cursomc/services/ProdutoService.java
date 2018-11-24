package com.rafael.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.cursomc.domain.Categorias;
import com.rafael.cursomc.domain.Produtos;
import com.rafael.cursomc.repositories.CategoriaRepository;
import com.rafael.cursomc.repositories.ProdutoRepository;
import com.rafael.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository rep;
	
	
	@Autowired
	private CategoriaRepository repCategoria;
	
	public Produtos find(Integer cdProduto){
		  Optional<Produtos> obj  = rep.findById(cdProduto);
		  
		  if(obj == null) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Código: " + cdProduto +
					 ", Tipo: " + Produtos.class.getName());  
		  }
		  
		  return obj.orElse(null);
	}
	
	public Page<Produtos> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		
		List<Categorias>  categorias = repCategoria.findAllById(ids);
		
		//return rep.search(nome,categorias,pageRequest);
		return rep.findDistinctBydsProdutoContainingAndCategoriasIn(nome,categorias,pageRequest);
	}
}
