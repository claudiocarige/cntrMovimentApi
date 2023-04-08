package com.claudiocarige.CntrMovimentApi.domain.enums;

public enum CategoryCntr {

	IMPORT(0, "IMPORT"),
	EXPORT(1, "EXPORT"),
	STOPPED(2, "STOPPED");

	private Integer code;
	private String description;

	private CategoryCntr(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static CategoryCntr toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for (CategoryCntr x : CategoryCntr.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo Inválido");
	}
}
