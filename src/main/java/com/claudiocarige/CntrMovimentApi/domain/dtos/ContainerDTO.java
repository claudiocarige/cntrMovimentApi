package com.claudiocarige.CntrMovimentApi.domain.dtos;

import java.io.Serializable;

import com.claudiocarige.CntrMovimentApi.domain.Client;
import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.enums.CategoryCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.StatusCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.TypeCntr;

public class ContainerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String cntrNumber;

	private StatusCntr statusCntr;

	private TypeCntr typeCntr;

	private CategoryCntr categoryCntr;

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
