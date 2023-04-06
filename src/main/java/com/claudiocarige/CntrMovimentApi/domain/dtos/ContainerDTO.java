package com.claudiocarige.CntrMovimentApi.domain.dtos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.claudiocarige.CntrMovimentApi.domain.Client;
import com.claudiocarige.CntrMovimentApi.domain.enums.CategoryCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.StatusCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.TypeCntr;

public class ContainerDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull(message = "O campo CONTAIRER é requerido!")
	private String cntrNumber;
	@NotNull(message = "O STATUS do CONTAIRER é requerido!")
	private StatusCntr statusCntr;
	@NotNull(message = "O TIPO do CONTAIRER é requerido!")
	private TypeCntr typeCntr;
	@NotNull(message = "A CATEGORIA do CONTAIRER é requerido!")
	private CategoryCntr categoryCntr;
	
	private Client client;

	
}
