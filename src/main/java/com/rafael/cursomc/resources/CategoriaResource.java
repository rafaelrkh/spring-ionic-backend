package com.rafael.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafael.cursomc.domain.Categorias;
import com.rafael.cursomc.dto.CategoriaDTO;
import com.rafael.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	
	/*
	@RequestMapping(method=RequestMethod.GET)
	public List<Categorias> listar() {	
		
		Categorias c1 = new Categorias(1, "Informática");
		Categorias c2 = new Categorias(2, "Escritório");
		
		List<Categorias> list  = new ArrayList<Categorias>();
		list.add(c1);
		list.add(c2);
		
		return list;
	} */
	
	@RequestMapping(value="/{cdCategoria}", method=RequestMethod.GET)
	public ResponseEntity<Categorias> find(@PathVariable Integer cdCategoria) {	
		
		Categorias obj = service.find(cdCategoria);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
		Categorias obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{cdCategoria}").buildAndExpand(obj.getCdCategoria()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{cdCategoria}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer cdCategoria){
		Categorias obj = service.fromDTO(objDto);
		obj.setCdCategoria(cdCategoria); 
		
		
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
		 
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{cdCategoria}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer cdCategoria) {
		service.delete(cdCategoria);
		 
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {	
		
		List<Categorias> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().
				map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dsCategoria") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {			
		
		
		Page<Categorias> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
		
	}

}
