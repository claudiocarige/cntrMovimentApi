package com.claudiocarige.CntrMovimentApi.domain.dtos;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.claudiocarige.CntrMovimentApi.domain.Users;

public class UsersDTO {

	private UUID id;
	@NotNull(message = "Username é requerido!")
	private String username;
	@NotNull(message = "Password é requerido!")
	private String password;
	@NotNull(message = "A Role é requerida!")
	private Boolean admin;

	public UsersDTO() {
	}

	public UsersDTO(Users users) {
		super();
		this.id = users.getId();
		this.username = users.getUsername();
		this.password = users.getPassword();
		this.admin = users.getAdmin();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}
