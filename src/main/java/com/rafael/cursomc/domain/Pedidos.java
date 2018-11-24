package com.rafael.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedidos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cdPedido;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dtPedido;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private Pagamentos pagamento;
	
	@ManyToOne
	@JoinColumn(name="cdCliente")
	private Clientes cliente;
	
	@ManyToOne
	@JoinColumn(name="cdEnderecoEntrega")
	private Enderecos enderecoEntrega;
	
	//Conjunto de itens
	@OneToMany(mappedBy="id.pedido")
	private Set<ItensPedido> itens = new HashSet<>();
	
	public Pedidos() {
		
	}
	
	public double getValorTotal() {
		double soma = 0.0;
		
		for(ItensPedido ip : itens ) {
			soma = soma + ip.getSubTotal();
		}
		
		return soma;
	}

	public Pedidos(Integer cdPedido, Date dtPedido,  Clientes cliente, Enderecos enderecoEntrega) {
		super();
		this.cdPedido = cdPedido;
		this.dtPedido = dtPedido;
		//this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}

	public Integer getCdPedido() {
		return cdPedido;
	}

	public void setCdPedido(Integer cdPedido) {
		this.cdPedido = cdPedido;
	}

	public Date getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}

	public Pagamentos getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamentos pagamento) {
		this.pagamento = pagamento;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Enderecos getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Enderecos enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	
	public Set<ItensPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItensPedido> itens) {
		this.itens = itens;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdPedido == null) ? 0 : cdPedido.hashCode());
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
		Pedidos other = (Pedidos) obj;
		if (cdPedido == null) {
			if (other.cdPedido != null)
				return false;
		} else if (!cdPedido.equals(other.cdPedido))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Pedidos número: ");
		builder.append(getCdPedido());
		builder.append(", Data: ");
		builder.append(sdf.format(getDtPedido()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getDsNome());
		builder.append(", Situação do pagamento: ");
		builder.append(getPagamento().getEstadoPagamento().getDsEstadoPagamento());
		builder.append("\n Detalhes: \n");

		for(ItensPedido ip: getItens()) {
			builder.append(ip.toString());
		}
		
		builder.append("Valor total: ");
		builder.append(nf.format(getValorTotal()));
		
		return builder.toString();
	}
	
	

	
}
