package com.claudiocarige.CntrMovimentApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.CntrMovimentApi.domain.ContainerMoviment;

public interface ContainerMovimentRepository extends JpaRepository<ContainerMoviment, Long>{

}
