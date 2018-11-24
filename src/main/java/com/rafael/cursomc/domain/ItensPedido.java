package com.rafael.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItensPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Integer quantidade;
	private Double vlrUnitario;	
	
	public ItensPedido() {
		
	}

	public ItensPedido(Pedidos pedido, Produtos produto, Integer quantidade, Double vlrUnitario) {
		super();
		//this.id = id;
		id.setPedido(pedido);
		id.setProduto(produto);
		this.quantidade = quantidade;
		this.vlrUnitario = vlrUnitario;
	}
	
	public void setPedido(Pedidos pedido) {
		id.setPedido(pedido);
	}
	
	public void setProduto(Produtos produto) {
		id.setProduto(produto);
	}
	
	public double getSubTotal() {
		return (vlrUnitario* quantidade);
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(Double vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}
	
	@JsonIgnore
	public Pedidos getPedido() {
		return id.getPedido();
	}
	
	
	public Produtos getProduto() {
		return id.getProduto();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ItensPedido other = (ItensPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getDsProduto());
		builder.append(", Qtde: ");
		builder.append(getQuantidade());
		builder.append(", Valor unitário: ");
		builder.append(nf.format(getVlrUnitario()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	
	
	
}
