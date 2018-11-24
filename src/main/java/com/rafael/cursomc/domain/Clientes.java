package com.rafael.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafael.cursomc.domain.enums.Perfil;
import com.rafael.cursomc.domain.enums.TipoCliente;

@Entity
public class Clientes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cdCliente;
	private String dsNome;

	@Column(unique = true)
	private String dsEmail;
	private String dsCpfCnpj;

	@JsonIgnore
	private String dsSenha;

	private Integer tipo;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Enderecos> enderecos = new ArrayList<>();

	// Conjunto de telefones para o cliente, ao invés de criar uma nova tabela
	@ElementCollection
	@CollectionTable(name = "telefone")
	private Set<String> telefones = new HashSet<>(); // Entidade fraca

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedidos> pedidos = new ArrayList<Pedidos>();
	
	public Clientes() {
		addPerfil(Perfil.CLIENTE); //Todos os usuários, por padrão serão clientes
	}

	public Clientes(Integer cdCliente, String dsNome, String dsEmail, String dsCpfCcnpj, String dsSenha,
			TipoCliente tipo) {
		super();
		this.cdCliente = cdCliente;
		this.dsNome = dsNome;
		this.dsEmail = dsEmail;
		this.dsCpfCnpj = dsCpfCcnpj;
		this.dsSenha = dsSenha;
		this.tipo = (tipo == null) ? null : tipo.getCdTipoCliente();
		addPerfil(Perfil.CLIENTE);
	}

	public Integer getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}

	public String getDsNome() {
		return dsNome;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsCpfCnpj() {
		return dsCpfCnpj;
	}

	public void setDsCpfCnpj(String dsCpfCnpj) {
		this.dsCpfCnpj = dsCpfCnpj;
	}

	public String getDsSenha() {
		return dsSenha;
	}

	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCdTipoCliente();
	}

	public List<Enderecos> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Enderecos> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<Pedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCdPerfil());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCliente == null) ? 0 : cdCliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clientes other = (Clientes) obj;
		if (cdCliente == null) {
			if (other.cdCliente != null)
				return false;
		} else if (!cdCliente.equals(other.cdCliente))
			return false;
		return true;
	}

}
