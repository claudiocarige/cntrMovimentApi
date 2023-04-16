package com.claudiocarige.CntrMovimentApi.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.CntrMovimentApi.domain.Users;

public interface UsersReporitory extends JpaRepository<Users, UUID>{
	
	Optional<Users> findByUsername(String username);
}
