package com.claudiocarige.CntrMovimentApi.domain.enums;

public enum Perfil {

	ADMIN(0, "ADMIN"), 
	USER(1, "USER");

	private Integer code;
	private String description;

	private Perfil(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static Perfil toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for (Perfil x : Perfil.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo Inválido");
	}
}
