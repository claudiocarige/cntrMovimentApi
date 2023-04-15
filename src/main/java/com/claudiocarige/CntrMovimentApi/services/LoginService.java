package com.claudiocarige.CntrMovimentApi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claudiocarige.CntrMovimentApi.domain.Users;
import com.claudiocarige.CntrMovimentApi.domain.dtos.UsersDTO;
import com.claudiocarige.CntrMovimentApi.repositories.UsersReporitory;
import com.claudiocarige.CntrMovimentApi.services.exception.DataIntegrityViolationException;

@Service
public class LoginService {

	@Autowired
	private UsersReporitory usersReporitory;
	

	public Users insert(UsersDTO usersDTO) {
		usersDTO.setId(null);
		Optional<Users> userTest = usersReporitory.findByUsername(usersDTO.getUsername());
		if(userTest.isPresent() && usersDTO.getUsername().equals(userTest.get().getUsername())) {
			throw new DataIntegrityViolationException("Usuário já cadastrado!");
		}
		Users newUsers = transfDTO(usersDTO);
		return usersReporitory.save(newUsers);
	}
	
	public Users transfDTO(UsersDTO usersDTO) { 
		Users users = new Users();
		users.setId(usersDTO.getId());
		users.setUsername(usersDTO.getUsername());
		users.setPassword(usersDTO.getPassword());
		users.setAdmin(usersDTO.getAdmin());
		return users;
	}
	
}
