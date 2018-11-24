package com.rafael.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cdEstadoPagamento;
	private String dsEstadoPagamento;
	
	private EstadoPagamento(int cdEstadoPagamento, String dsEstadoPagamento) {
		this.cdEstadoPagamento = cdEstadoPagamento;
		this.dsEstadoPagamento = dsEstadoPagamento;
	}
	
	public int getCdEstadoPagamento() {
		return cdEstadoPagamento;
	}
	
	public String getDsEstadoPagamento() {
		return dsEstadoPagamento;
	}
	
public static EstadoPagamento toEnum(Integer cdEstadoPagamento) {
	
		if(cdEstadoPagamento == null) {
			return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cdEstadoPagamento.equals(x.getCdEstadoPagamento())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + cdEstadoPagamento);
	}
}
