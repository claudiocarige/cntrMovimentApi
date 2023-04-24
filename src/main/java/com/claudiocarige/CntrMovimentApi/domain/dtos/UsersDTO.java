package com.claudiocarige.CntrMovimentApi.domain.dtos;

import javax.validation.constraints.NotBlank;

import com.claudiocarige.CntrMovimentApi.domain.Users;

public class UsersDTO {

	@NotBlank(message = "O campo USERNAME não pode ser vazio ou nulo")
	private String username;
	@NotBlank(message = "O campo PASSWORD não pode ser vazio ou nulo")
	private String password;

	public UsersDTO() {
	}

	public UsersDTO(Users users) {
		this.username = users.getUsername();
		this.password = users.getPassword();
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

}
