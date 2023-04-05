package com.claudiocarige.CntrMovimentApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.CntrMovimentApi.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
