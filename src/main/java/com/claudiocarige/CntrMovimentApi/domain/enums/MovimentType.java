package com.claudiocarige.CntrMovimentApi.domain.enums;

public enum MovimentType {

	DEPARTURE(0, "DEPARTURE"), 
	ARRIVAL(1, "ARRIVAL"),
	GATEIN(2, "GATEIN"),
	GATEOUT(3, "GATEOUT"),
	REPOSITION(4, "REPOSITION"),
	WEIGHING(5, "WEIGHING"),
	SCANNER(6, "SCANNER");

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
