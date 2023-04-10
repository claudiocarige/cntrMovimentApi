package com.claudiocarige.CntrMovimentApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.CntrMovimentApi.domain.ContainerMoviment;
import com.claudiocarige.CntrMovimentApi.repositories.ContainerMovimentRepository;
import com.claudiocarige.CntrMovimentApi.services.exception.NoSuchElementException;

@Service
public class ContainerMovimentService {

	@Autowired
	private ContainerMovimentRepository cntrMoveRepository;

	public List<ContainerMoviment> findAll() {
		return cntrMoveRepository.findAll();
	}

	public ContainerMoviment findById(Long id) {
		Optional<ContainerMoviment> cntr = cntrMoveRepository.findById(id);
		return cntr.orElseThrow(() -> new NoSuchElementException("Movimentação não encontrada!"));
	}
}
