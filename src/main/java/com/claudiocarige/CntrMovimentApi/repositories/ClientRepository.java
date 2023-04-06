package com.claudiocarige.CntrMovimentApi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.CntrMovimentApi.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Optional<Client> findByCnpj(String cnpj);

}
