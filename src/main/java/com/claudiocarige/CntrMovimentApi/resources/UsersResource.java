package com.claudiocarige.CntrMovimentApi.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudiocarige.CntrMovimentApi.domain.Users;
import com.claudiocarige.CntrMovimentApi.domain.dtos.UsersDTO;
import com.claudiocarige.CntrMovimentApi.services.LoginService;

@RestController
@RequestMapping(value = "/api/users")
public class UsersResource {
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<UsersDTO> insert(@RequestBody @Valid UsersDTO usersDTO){
		usersDTO.setPassword(encoder.encode(usersDTO.getPassword()));
		Users users = loginService.insert(usersDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(users.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
