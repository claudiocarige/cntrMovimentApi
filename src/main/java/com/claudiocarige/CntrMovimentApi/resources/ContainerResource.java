package com.claudiocarige.CntrMovimentApi.resources;

import java.net.URI;
import java.util.List;

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
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerDTO;
import com.claudiocarige.CntrMovimentApi.services.ContainerService;

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
		cntrDTO = cntrService.formatCntr(cntrDTO);
	 	Container newCntr = cntrService.insert(cntrDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newCntr.getId()).toUri();
	 	return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ContainerDTO> update(@PathVariable Long id, @Valid @RequestBody ContainerDTO cntrDTO){
		cntrDTO = cntrService.formatCntr(cntrDTO);
		Container cntr = cntrService.update(cntrDTO, id);
		return ResponseEntity.ok().body(new ContainerDTO(cntr));
	}
}
