package com.rafael.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Produtos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cdProduto;
	private String dsProduto;
	private Double quantidadeEstoque;
	private Double preco;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", 
	           joinColumns = @JoinColumn(name = "cdProduto"),
	           inverseJoinColumns = @JoinColumn(name = "cdCategoria")) 
	private List<Categorias> categorias = new ArrayList<>();
	
	//Conjunto de itens
	@JsonIgnore
	@OneToMany(mappedBy="id.produto")
    private Set<ItensPedido> itens = new HashSet<>();
    
    //Retorna os pedidos que contenham esse produto
	@JsonIgnore
    public List<Pedidos> getPedidos(){
    	List<Pedidos> lista = new ArrayList<>();
    	
    	for(ItensPedido x : itens) {
    		lista.add(x.getPedido());
    	}
    	
    	return lista;
    }

	public Produtos() {

	}

	public Produtos(Integer cdProduto, String dsProduto, Double quantidadeEstoque, Double preco) {
		super();
		this.cdProduto = cdProduto;
		this.dsProduto = dsProduto;
		this.quantidadeEstoque = quantidadeEstoque;
		this.preco = preco;
	}


	public Integer getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(Integer cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public List<Categorias> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categorias> categorias) {
		this.categorias = categorias;
	}	
	
	public Set<ItensPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItensPedido> itens) {
		this.itens = itens;
	}	
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdProduto == null) ? 0 : cdProduto.hashCode());
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
		Produtos other = (Produtos) obj;
		if (cdProduto == null) {
			if (other.cdProduto != null)
				return false;
		} else if (!cdProduto.equals(other.cdProduto))
			return false;
		return true;
	}

}
