package com.claudiocarige.CntrMovimentApi.repositories;

import java.time.LocalDateTime;
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
	
	@Query("SELECT cm.container FROM ContainerMoviment cm WHERE cm.container.categoryCntr = :category AND cm.initialDate BETWEEN :startDate AND :endDate")
	List<Container> findContainersByCategoryAndDate(@Param("category") CategoryCntr category,
			@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
