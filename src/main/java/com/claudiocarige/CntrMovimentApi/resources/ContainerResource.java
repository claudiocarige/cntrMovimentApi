package com.claudiocarige.CntrMovimentApi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudiocarige.CntrMovimentApi.domain.Container;
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
 }
