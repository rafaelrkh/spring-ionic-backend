package com.rafael.cursomc.domain.enums;

public enum TipoCliente {

   PESSOAFISICA(1, "Pessoa Física"), 
   PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	public int cdTipoCliente;
	private String dsTipoCliente;
	
	private TipoCliente(int cdTipoCliente, String dsTipoCliente) {
		this.cdTipoCliente = cdTipoCliente;
		this.dsTipoCliente = dsTipoCliente;
	}
	
	public int getCdTipoCliente() {
		return cdTipoCliente;		
	}
	
	public String getDsTipoCliente() {
		return dsTipoCliente;
	}
	
	public static TipoCliente toEnum(Integer cd_tipo_cliente) {
		
		if(cd_tipo_cliente == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(cd_tipo_cliente.equals(x.getCdTipoCliente())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + cd_tipo_cliente);
	}
}
