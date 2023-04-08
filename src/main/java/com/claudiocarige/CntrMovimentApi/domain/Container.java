package com.claudiocarige.CntrMovimentApi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.claudiocarige.CntrMovimentApi.domain.enums.CategoryCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.StatusCntr;
import com.claudiocarige.CntrMovimentApi.domain.enums.TypeCntr;

@Entity
@Table(name = "tb_container")
public class Container implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, length = 11)
	private String cntrNumber;
	
	@Enumerated
	private StatusCntr statusCntr;
	@Enumerated
	private TypeCntr typeCntr;
	@Enumerated
	private CategoryCntr categoryCntr;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public Container() {
	}

	public Container(Long id, String cntrNumber, StatusCntr statusCntr, TypeCntr typeCntr, CategoryCntr categoryCntr,
			Client client) {
		super();
		this.id = id;
		this.cntrNumber = cntrNumber;
		this.statusCntr = statusCntr;
		this.typeCntr = typeCntr;
		this.categoryCntr = categoryCntr;
		this.client = client;
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

	@Override
	public int hashCode() {
		return Objects.hash(cntrNumber, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Container other = (Container) obj;
		return Objects.equals(cntrNumber, other.cntrNumber) && Objects.equals(id, other.id);
	}

}
