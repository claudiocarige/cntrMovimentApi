package com.claudiocarige.CntrMovimentApi.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudiocarige.CntrMovimentApi.domain.ContainerMoviment;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerMovimentDTO;
import com.claudiocarige.CntrMovimentApi.services.ContainerMovimentService;

@RestController
@RequestMapping(value = "/api/cntrmoviment")
public class ContainerMovimentResource {

	@Autowired
	private ContainerMovimentService cntrMoveService;
	
	@GetMapping
	public ResponseEntity<List<ContainerMovimentDTO>> findAll(){
		List<ContainerMoviment> list = cntrMoveService.findAll();
		List<ContainerMovimentDTO> cntrMoveDTO = list.stream().
										map(obj -> new ContainerMovimentDTO(obj))
										.collect(Collectors.toList());
		return ResponseEntity.ok().body(cntrMoveDTO);
	}
}
