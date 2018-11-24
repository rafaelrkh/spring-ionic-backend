package com.rafael.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.cursomc.domain.Categorias;
import com.rafael.cursomc.dto.CategoriaDTO;
import com.rafael.cursomc.repositories.CategoriaRepository;
import com.rafael.cursomc.services.exceptions.DataIntegrityException;
import com.rafael.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository rep;
	
	public Categorias find(Integer cdCategoria){
		  Optional<Categorias> obj  = rep.findById(cdCategoria);
		  
		  if(obj == null) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Código: " + cdCategoria +
					 ", Tipo: " + Categorias.class.getName());  
		  }
		  
		  return obj.orElse(null);
	}
	
	public Categorias insert(Categorias obj) {
		obj.setCdCategoria(null); // O Codigo deve ser nulo, pois o método "save" entende que é uma inserção e não alteração
		return rep.save(obj);
	}
	
	public Categorias update(Categorias obj) {

		// Verificando existência de código da categoria
		Categorias newObj = find(obj.getCdCategoria());
		 updateData(newObj, obj); // Atualiza os dados do objeto que criou de acordo com o passado no método

		return rep.save(newObj);
	}
	
	public void delete(Integer cdCategoria) {
		
		//Verificando existência de código da categoria
		find(cdCategoria);
		
		try {
		
			rep.deleteById(cdCategoria);
			
		} catch(DataIntegrityViolationException e) { //Se houver alguma integridade de categoria e produto, esta excessão aborta a exclusão
			
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}

	public List<Categorias> findAll(){
		return rep.findAll();
	}

	//Retorna uma página de Categorias, fazendo a "Paginação"
	public Page<Categorias> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return rep.findAll(pageRequest);
	}
	
	public Categorias fromDTO(CategoriaDTO objDTO) {
		return new Categorias(objDTO.getCdCategoria(), objDTO.getDsCategoria());
	}
	
	private void  updateData(Categorias newObj, Categorias obj) {
		newObj.setDsCategoria(obj.getDsCategoria());
	}
	
}
