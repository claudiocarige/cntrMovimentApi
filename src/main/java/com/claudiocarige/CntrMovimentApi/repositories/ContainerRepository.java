package com.claudiocarige.CntrMovimentApi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.CntrMovimentApi.domain.Container;

public interface ContainerRepository extends JpaRepository<Container, Long> {

	Optional<Container> findByCntrNumber(String cntrNumber);
}
