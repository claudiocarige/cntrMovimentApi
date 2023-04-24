package com.claudiocarige.CntrMovimentApi.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.claudiocarige.CntrMovimentApi.domain.Client;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "O campo NOME é requerido não pode ser vazio ou nulo!")
	private String name;
	@NotBlank(message = "O campo CNPJ é requerido não pode ser vazio ou nulo!")
	private String cnpj;
	@NotBlank(message = "O campo CONTATO é requerido não pode ser vazio ou nulo!")
	private String contact;
	@NotBlank(message = "O campo E-MAIL é requerido não pode ser vazio ou nulo!")
	private String email;
	
	
	public ClientDTO() {
	}

	public ClientDTO(Client obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cnpj = obj.getCnpj();
		this.contact = obj.getContact();
		this.email = obj.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
