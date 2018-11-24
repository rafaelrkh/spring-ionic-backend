package com.rafael.cursomc.domain.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cdPerfil;
	private String dsPerfil;
	
	private Perfil(int cdPerfil, String dsPerfil) {
		this.cdPerfil = cdPerfil;
		this.dsPerfil = dsPerfil;
	}

	public int getCdPerfil() {
		return cdPerfil;
	}


	public String getDsPerfil() {
		return dsPerfil;
	}


	public static Perfil toEnum(Integer cdPerfil) {
		
		if(cdPerfil == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(cdPerfil.equals(x.getCdPerfil())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + cdPerfil);
	}
	
}