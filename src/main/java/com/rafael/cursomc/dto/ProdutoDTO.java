package com.rafael.cursomc.dto;

import java.io.Serializable;

import com.rafael.cursomc.domain.Produtos;

public class ProdutoDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	

	private Integer cdProduto;
	private String dsProduto;
	private Double quantidadeEstoque;
	
	public ProdutoDTO() {
		
		
	}
	
	public ProdutoDTO(Produtos obj) {
		cdProduto = obj.getCdProduto();
		dsProduto = obj.getDsProduto();
		quantidadeEstoque = obj.getQuantidadeEstoque();
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
	
	
}
