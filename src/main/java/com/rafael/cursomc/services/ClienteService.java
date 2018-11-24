package com.rafael.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rafael.cursomc.domain.Cidades;
import com.rafael.cursomc.domain.Clientes;
import com.rafael.cursomc.domain.Enderecos;
import com.rafael.cursomc.domain.enums.Perfil;
import com.rafael.cursomc.domain.enums.TipoCliente;
import com.rafael.cursomc.dto.ClienteDTO;
import com.rafael.cursomc.dto.ClienteNewDTO;
import com.rafael.cursomc.repositories.ClienteRepository;
import com.rafael.cursomc.repositories.EnderecoRepository;
import com.rafael.cursomc.security.UserSS;
import com.rafael.cursomc.services.exceptions.AuthorizationException;
import com.rafael.cursomc.services.exceptions.DataIntegrityException;
import com.rafael.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	

	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository rep;
	
	@Autowired
	private EnderecoRepository repEnd;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;

	public Clientes find(Integer cdCliente) {
		
		UserSS user = UserService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN) && !cdCliente.equals(user.getCdUsuario())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		Optional<Clientes> obj = rep.findById(cdCliente);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Código: " + cdCliente + ", Tipo: " + Clientes.class.getName());
		}

		return obj.orElse(null);
	}
	
	public Clientes insert(Clientes obj) {
		obj.setCdCliente(null); // O Codigo deve ser nulo, pois o método "save" entende que é uma inserção e não alteração
		
		obj = rep.save(obj);
		repEnd.saveAll(obj.getEnderecos());
		
		return obj;
	}
	

	public Clientes update(Clientes obj) {

		// Verificando existência de código da categoria
		Clientes newObj = find(obj.getCdCliente());
		 updateData(newObj, obj); // Atualiza os dados do objeto que criou de acordo com o passado no método

		return rep.save(newObj);
	}

	public void delete(Integer cdCliente) {

		// Verificando existência de código da categoria
		find(cdCliente);

		try {

			rep.deleteById(cdCliente);

		} catch (DataIntegrityViolationException e) { // Se houver alguma integridade de categoria e produto, esta excessão aborta a exclusão

			throw new DataIntegrityException("Não é possível excluir porque há pedidos para ele!");
		}
	}

	public List<Clientes> findAll() {
		return rep.findAll();
	}
	
	public Clientes findByDsEmail(String dsEmail) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !dsEmail.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
 		Clientes obj = rep.findByDsEmail(dsEmail);
 		
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getCdUsuario() + ", Tipo: " + Clientes.class.getName());
		}
		
		return obj;
	}

	// Retorna uma página de Clientes, fazendo a "Paginação"
	public Page<Clientes> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return rep.findAll(pageRequest);
	}

	public Clientes fromDTO(ClienteDTO objDto) {
		return new Clientes(objDto.getCdCliente(), objDto.getDsNome(), objDto.getDsEmail(), null, null, null);
	}
	
	public Clientes fromDTO(ClienteNewDTO objDto) {
		
		Clientes cli = new Clientes(null, objDto.getDsNome(), objDto.getDsEmail(), objDto.getDsCpfCnpj(), pe.encode(objDto.getDsSenha()), TipoCliente.toEnum(objDto.getTipo()));
		Cidades cid = new Cidades(objDto.getCdCidade(),null,null);
		Enderecos end = new Enderecos(null, objDto.getDsLogradouro(),objDto.getDsNumero(), objDto.getDsBairro(), objDto.getDsComplemento(), objDto.getDsCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getDsTelefone1());
		
		if(objDto.getDsTelefone2() != null) {
			cli.getTelefones().add(objDto.getDsTelefone2());
		}
		
		if(objDto.getDsTelefone3() != null) {
			cli.getTelefones().add(objDto.getDsTelefone3());
		}
		
		
		return cli;
	}
	
	private void updateData(Clientes newObj, Clientes obj) {
		newObj.setDsNome(obj.getDsNome());
		newObj.setDsEmail(obj.getDsEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getCdUsuario() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

	}

	
}
