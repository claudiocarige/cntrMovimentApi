package com.claudiocarige.CntrMovimentApi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerDTO;
import com.claudiocarige.CntrMovimentApi.services.ContainerService;
import com.claudiocarige.CntrMovimentApi.services.exception.DataIntegrityViolationException;

@RestController
@RequestMapping(value = "/api/cntr")
public class ContainerResource {

	@Autowired
	private ContainerService cntrService;
	
	@GetMapping
	public ResponseEntity<List<Container>> findAll(){
		List<Container> list = cntrService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Container> findById(@PathVariable Long id){
		Container cntr = cntrService.findById(id);
	    return ResponseEntity.ok().body(cntr);
	}
	
	@PostMapping
	public ResponseEntity<ContainerDTO> insert(@Valid @RequestBody ContainerDTO cntrDTO){
		cntrDTO.setCntrNumber(cntrDTO.getCntrNumber().replaceAll("[^a-zA-Z0-9]+", ""));
	 	if (!cntrDTO.getCntrNumber().matches("[A-Z]{4}[0-9]{7}")) {
			throw new DataIntegrityViolationException("Numero do CNTR deve conter 4 letras MAIÚSCULAS e 7 Numeros!");
		}
	 	Container newCntr = cntrService.insert(cntrDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newCntr.getId()).toUri();
	 	return ResponseEntity.created(uri).build();
	}
 }
