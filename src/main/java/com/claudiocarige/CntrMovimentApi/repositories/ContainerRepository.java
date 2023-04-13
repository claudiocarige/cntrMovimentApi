package com.claudiocarige.CntrMovimentApi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.enums.CategoryCntr;

public interface ContainerRepository extends JpaRepository<Container, Long> {

	Optional<Container> findByCntrNumber(String cntrNumber);

	@Query("SELECT c FROM Container c WHERE c.categoryCntr = :category")
	List<Container> findByCategory(@Param("category") CategoryCntr categoryCntr);
}
