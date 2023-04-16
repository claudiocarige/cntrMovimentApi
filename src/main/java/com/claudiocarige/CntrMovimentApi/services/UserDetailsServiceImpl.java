package com.claudiocarige.CntrMovimentApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.claudiocarige.CntrMovimentApi.domain.Users;
import com.claudiocarige.CntrMovimentApi.repositories.UsersReporitory;
import com.claudiocarige.CntrMovimentApi.services.exception.NoSuchElementException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersReporitory usersReporitory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersReporitory.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementException("Usuário não encontrado na badse de dados!"));
		String [] roles = users.getAdmin() ? new String[] {"USER", "ADMIN"} : new String[] {"USER"};
		
		return User
				.builder()
				.username(users.getUsername())
				.password(users.getPassword())
				.roles(roles)
				.build(); 
	}
	

}
