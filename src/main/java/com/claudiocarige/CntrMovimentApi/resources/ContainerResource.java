package com.claudiocarige.CntrMovimentApi.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerDTO;
import com.claudiocarige.CntrMovimentApi.domain.enums.CategoryCntr;
import com.claudiocarige.CntrMovimentApi.services.ContainerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Movimentação de Container API REST")
@RestController
@RequestMapping(value = "/api/cntr")
public class ContainerResource {

	@Autowired
	private ContainerService cntrService;

	@ApiOperation(produces = "Gera saída JSON", value = "Retorna uma Lista todos os Containers")
	@GetMapping
	public ResponseEntity<List<ContainerDTO>> findAll() {
		List<Container> list = cntrService.findAll();
		List<ContainerDTO> listDTO = list.stream().map(x -> new ContainerDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation(produces = "Gera saída JSON", value = "Retorna um Container específico")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContainerDTO> findById(@PathVariable Long id) {
		Container cntr = cntrService.findById(id);
		return ResponseEntity.ok().body(new ContainerDTO(cntr));
	}

	@ApiOperation(produces = "Gera saída JSON", value = "Retorna um Container por sua categoria")
	@GetMapping(value = "/category/{id}")
	public ResponseEntity<List<ContainerDTO>> findContainerByCategory(@PathVariable Integer id) {
		List<Container> cntrs = cntrService.findByCategory(id);
		List<ContainerDTO> cntrsDTO = cntrs.stream().map(x -> new ContainerDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cntrsDTO);
	}

	@ApiOperation(produces = "Gera saída JSON", value = "Retorna um Container por sua categoria e um intervalo de datas")
	@GetMapping("/category/{category}/dates")
	public ResponseEntity<List<ContainerDTO>> findContainersByCategoryAndDate(
			@PathVariable("category") String category,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime startDate,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime endDate) {
		List<Container> cntrs = cntrService.findContainersByCategoryAndDate(CategoryCntr.valueOf(category.toUpperCase()), startDate, endDate);
		List<ContainerDTO> cntrsDTO = cntrs.stream().map(x -> new ContainerDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cntrsDTO);
	}

	@ApiOperation(produces = "Aceita entrada Json", value = "Insere um novo Container")
	@PostMapping
	public ResponseEntity<ContainerDTO> insert(@Valid @RequestBody ContainerDTO cntrDTO) {
		cntrDTO = cntrService.formatCntr(cntrDTO);
		Container newCntr = cntrService.insert(cntrDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newCntr.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(produces = "Aceita entrada Json",  value = "Atualiza um Container")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ContainerDTO> update(@PathVariable Long id, @Valid @RequestBody ContainerDTO cntrDTO) {
		cntrDTO = cntrService.formatCntr(cntrDTO);
		Container cntr = cntrService.update(cntrDTO, id);
		return ResponseEntity.ok().body(new ContainerDTO(cntr));
	}

	@ApiOperation(produces = "Aceita entrada Json", value = "Atualiza um Cliente de um Container.")
	@PutMapping(value = "/updateclient/{id}")
	public ResponseEntity<ContainerDTO> UpdateClient(@PathVariable Long id, @Valid @RequestBody ContainerDTO cntrDTO) {
		cntrDTO = cntrService.formatCntr(cntrDTO);
		Container cntr = cntrService.updateClientCntr(id, cntrDTO);
		return ResponseEntity.ok().body(new ContainerDTO(cntr));
	}
}