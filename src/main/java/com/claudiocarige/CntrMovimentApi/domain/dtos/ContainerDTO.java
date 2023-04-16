package com.claudiocarige.CntrMovimentApi.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.claudiocarige.CntrMovimentApi.domain.Client;
import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.enums.CategoryCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.StatusCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.TypeCntr;

public class ContainerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "O numero do CNTR não pode ser vazio ou nulo!")
	private String cntrNumber;
	@NotBlank(message = "O campo STATUS não pode ser vazio ou nulo")
	private StatusCntr statusCntr;
	@NotBlank(message = "O campo TIPO não pode ser vazio ou nulo")
	private TypeCntr typeCntr;
	@NotBlank(message = "O campo CATEGORIA não pode ser vazio ou nulo")
	private CategoryCntr categoryCntr;
	@NotNull(message = "O campo CLIENTE é requerido")
	private Client client;

	public ContainerDTO() {
	}

	public ContainerDTO(Container cntr) {
		super();
		this.id = cntr.getId();
		this.cntrNumber = cntr.getCntrNumber();
		this.statusCntr = cntr.getStatusCntr();
		this.typeCntr = cntr.getTypeCntr();
		this.categoryCntr = cntr.getCategoryCntr();
		this.client = cntr.getClient();
	}
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCntrNumber() {
		return cntrNumber;
	}

	public void setCntrNumber(String cntrNumber) {
		this.cntrNumber = cntrNumber;
	}

	public StatusCntr getStatusCntr() {
		return statusCntr;
	}

	public void setStatusCntr(StatusCntr statusCntr) {
		this.statusCntr = statusCntr;
	}

	public TypeCntr getTypeCntr() {
		return typeCntr;
	}

	public void setTypeCntr(TypeCntr typeCntr) {
		this.typeCntr = typeCntr;
	}

	public CategoryCntr getCategoryCntr() {
		return categoryCntr;
	}

	public void setCategoryCntr(CategoryCntr categoryCntr) {
		this.categoryCntr = categoryCntr;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
