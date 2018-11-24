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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafael.cursomc.domain.Clientes;
import com.rafael.cursomc.dto.ClienteDTO;
import com.rafael.cursomc.dto.ClienteNewDTO;
import com.rafael.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	
	
/*	
 * @RequestMapping(method=RequestMethod.GET)
 * public List<Clientes> listar() {			
		
		Clientes cli1 = new Clientes(1, "Maria Silva", "maria@gmail.com", "07003999684", "******", TipoCliente.PESSOAFISICA);
		
		List<Clientes> list  = new ArrayList<Clientes>();
		list.add(cli1);
		
		return list;
	} */
	
	@RequestMapping(value="/{cdCliente}", method=RequestMethod.GET)
	public ResponseEntity<Clientes> find(@PathVariable Integer cdCliente) {	
		
		Clientes obj = service.find(cdCliente);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/dsEmail", method=RequestMethod.GET)
	public ResponseEntity<Clientes> find(@RequestParam(value="value") String dsEmail) {
		Clientes obj = service.findByDsEmail(dsEmail);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Clientes obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{cdCliente}").buildAndExpand(obj.getCdCliente()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{cdCliente}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer cdCliente){
		Clientes obj = service.fromDTO(objDto);
		obj.setCdCliente(cdCliente); 
		
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
		 
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{cdCliente}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer cdCliente) {
		service.delete(cdCliente);
		 
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {	
		
		List<Clientes> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().
				map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dsCategoria") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {			
		
		
		Page<Clientes> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile multipartFile){
		URI uri = service.uploadProfilePicture(multipartFile);
		
		return ResponseEntity.created(uri).build();
	}
	

}
