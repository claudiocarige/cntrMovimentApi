package com.claudiocarige.CntrMovimentApi.domain.enums;

public enum TypeCntr {

	HC20(0, "HC20"), 
	HC40(1, "HC40");

	private Integer code;
	private String description;

	private TypeCntr(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static TypeCntr toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for (TypeCntr x : TypeCntr.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo Inválido");
	}
}
