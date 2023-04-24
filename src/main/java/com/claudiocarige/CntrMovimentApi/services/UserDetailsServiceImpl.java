	package com.claudiocarige.CntrMovimentApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.claudiocarige.CntrMovimentApi.domain.Users;
import com.claudiocarige.CntrMovimentApi.repositories.UsersReporitory;
import com.claudiocarige.CntrMovimentApi.security.UserSS;
import com.claudiocarige.CntrMovimentApi.services.exception.NoSuchElementException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersReporitory usersReporitory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersReporitory.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementException("Usuário não encontrado na badse de dados!"));
		
		return new UserSS(user.getId(), user.getUsername(), user.getPassword(), user.getPerfis());
	}
	

}
