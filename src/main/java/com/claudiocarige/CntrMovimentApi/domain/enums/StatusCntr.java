package com.claudiocarige.CntrMovimentApi.domain.enums;

public enum StatusCntr {

	FULL(1, "CHEIO"), 
	EMPTY(2, "VAZIO");

	private Integer code;
	private String description;

	private StatusCntr(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static StatusCntr toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for (StatusCntr x : StatusCntr.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo Inválido");
	}
}
