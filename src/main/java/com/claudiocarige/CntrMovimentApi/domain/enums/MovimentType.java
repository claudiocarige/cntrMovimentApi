package com.claudiocarige.CntrMovimentApi.domain.enums;

public enum MovimentType {

	DEPARTURE(1, "DEPARTURE"), 
	ARRIVAL(2, "ARRIVAL"),
	GATEIN(3, "GATEIN"),
	GATEOUT(2, "GATEOUT"),
	REPOSITION(2, "REPOSITION"),
	WEIGHING(2, "WEIGHING"),
	SCANNER(2, "SCANNER");

	private Integer code;
	private String description;

	private MovimentType(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static MovimentType toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		for (MovimentType x : MovimentType.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo Inválido");
	}
}
