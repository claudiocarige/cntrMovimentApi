package com.claudiocarige.CntrMovimentApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.CntrMovimentApi.domain.Container;

public interface ContainerRepository extends JpaRepository<Container, Long> {

}
