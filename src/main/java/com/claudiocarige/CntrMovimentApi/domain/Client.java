package com.claudiocarige.CntrMovimentApi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Column(unique = true, length = 14)
	private String cnpj;
	private String contact;
	private String email;
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Container> containers = new ArrayList<>();

	public Client() {
	}

	public Client(Long id, String name, String cnpj, String contact, String email) {
		super();
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.contact = contact;
		this.email = email;
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

	public List<Container> getContainers() {
		return containers;
	}

	public void addContainers(Container containers) {
		this.containers.add(containers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(id, other.id);
	}

}
