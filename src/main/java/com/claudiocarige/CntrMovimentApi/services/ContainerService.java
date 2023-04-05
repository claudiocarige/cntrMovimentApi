package com.claudiocarige.CntrMovimentApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.repositories.ContainerRepository;

@Service
public class ContainerService {

	
	@Autowired
	private ContainerRepository cntrRepository;
	
	public List<Container> findAll(){
		return cntrRepository.findAll();
	}
	
	public Container findById(Long id) {
		Optional<Container> cntr = cntrRepository.findById(id);
		return cntr.orElseThrow();
	}
}
