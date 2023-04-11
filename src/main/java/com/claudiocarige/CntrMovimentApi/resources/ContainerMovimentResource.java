package com.claudiocarige.CntrMovimentApi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.ContainerMoviment;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerDTO;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerMovimentDTO;
import com.claudiocarige.CntrMovimentApi.services.ContainerMovimentService;
import com.claudiocarige.CntrMovimentApi.services.ContainerService;

@RestController
@RequestMapping(value = "/api/cntrmoviment")
public class ContainerMovimentResource {

	@Autowired
	private ContainerMovimentService cntrMoveService;
	
	@Autowired
	private ContainerService cntrService;
	
	@GetMapping
	public ResponseEntity<List<ContainerMovimentDTO>> findAll(){
		List<ContainerMoviment> list = cntrMoveService.findAll();
		List<ContainerMovimentDTO> cntrMoveDTO = list.stream().
										map(obj -> new ContainerMovimentDTO(obj))
										.collect(Collectors.toList());
		return ResponseEntity.ok().body(cntrMoveDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContainerMovimentDTO> findById(@PathVariable Long id){		
		ContainerMoviment cntr = cntrMoveService.findById(id);
		return ResponseEntity.ok().body(new ContainerMovimentDTO(cntr));
	
	}
	
	@PostMapping
	public ResponseEntity<ContainerMovimentDTO> insert(@Valid @RequestBody ContainerMovimentDTO cntrMoveDTO){
		ContainerMoviment cntr = cntrMoveService.insert(cntrMoveDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(cntr.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ContainerMovimentDTO> update(@PathVariable Long id, @Valid @RequestBody ContainerMovimentDTO cntrMoveDTO){
		Container cntr = cntrMoveDTO.getContainer(); 
		cntrMoveDTO.setContainer(cntrService.transformDTO(cntrService.formatCntr(new ContainerDTO(cntr))));
		ContainerMoviment cntrMove = cntrMoveService.update(id, cntrMoveDTO);
		return ResponseEntity.ok().body(new ContainerMovimentDTO(cntrMove));
	}
	
}
