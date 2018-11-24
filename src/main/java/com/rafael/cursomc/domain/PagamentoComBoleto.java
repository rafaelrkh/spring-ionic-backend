package com.rafael.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rafael.cursomc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamentos{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dtVencimento;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dtPagamento;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer cdPagamento, EstadoPagamento estadoPagamento, Pedidos pedido, Date dtVencimento, Date dtPagamento) {
		super(cdPagamento, estadoPagamento, pedido);
		this.dtVencimento = dtVencimento;
		this.dtPagamento = dtPagamento;
	}

	public Date getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}
	
	
}
