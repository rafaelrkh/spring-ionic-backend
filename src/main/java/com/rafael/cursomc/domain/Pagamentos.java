package com.rafael.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rafael.cursomc.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamentos  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer cdPagamento;
	
	private Integer estadoPagamento;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="cdPedido")
	@MapsId
	private Pedidos pedido;

	public Pagamentos() {
		
	}

	public Pagamentos(Integer cdPagamento, EstadoPagamento estadoPagamento, Pedidos pedido) {
		this.cdPagamento = cdPagamento;
		this.estadoPagamento = (estadoPagamento==null) ? null : estadoPagamento.getCdEstadoPagamento();
		this.pedido = pedido;
	}

	public Integer getCdPagamento() {
		return cdPagamento;
	}

	public void setCdPagamento(Integer cdPagamento) {
		this.cdPagamento = cdPagamento;
	}

	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento.getCdEstadoPagamento();
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdPagamento;
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
		Pagamentos other = (Pagamentos) obj;
		if (cdPagamento != other.cdPagamento)
			return false;
		return true;
	}
	
	
}
