package com.claudiocarige.CntrMovimentApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.CntrMovimentApi.domain.ContainerMoviment;
import com.claudiocarige.CntrMovimentApi.repositories.ContainerMovimentRepository;

@Service
public class ContainerMovimentService {

	@Autowired
	private ContainerMovimentRepository cntrMoveRepository;
	
	public List<ContainerMoviment> findAll() {
		return cntrMoveRepository.findAll();
	}
}
