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

import com.claudiocarige.CntrMovimentApi.domain.Client;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ClientDTO;
import com.claudiocarige.CntrMovimentApi.services.ClientService;
import com.claudiocarige.CntrMovimentApi.services.exception.DataIntegrityViolationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Movimentação de Container API REST")
@RestController
@RequestMapping(value = "/api/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	@ApiOperation(produces= "Aceita entrada Json", nickname= "Gera saída JSON",value = "Retorna uma lista de todos os clientes")
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findall() {
		List<Client> list = clientService.findAll();
		List<ClientDTO> listDTO = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@ApiOperation(produces= "Aceita entrada Json", nickname= "Gera saída JSON",value = "Retorna um cliente especifico")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		Client client = clientService.findById(id);
		return ResponseEntity.ok().body(new ClientDTO(client));
	}
	@ApiOperation(produces= "Aceita entrada Json", nickname= "Gera saída JSON",value = "Insere um novo cliente.")
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO clientDTO) {
		clientDTO = formatCnpj(clientDTO);
		Client client = clientService.insert(clientDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(produces= "Aceita entrada Json", nickname= "Gera saída JSON",value = "Atualiza um cliente.")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
		clientDTO = formatCnpj(clientDTO);
		Client client = clientService.update(id, clientDTO);
		return ResponseEntity.ok().body(new ClientDTO(client));
	}

	public ClientDTO formatCnpj(ClientDTO clientDTO) {
		clientDTO.setCnpj(clientDTO.getCnpj().replaceAll("[^\\d]", ""));
		if (clientDTO.getCnpj().length() != 14) {
			throw new DataIntegrityViolationException("O CNPJ deve conter 14 numeros");
		}
		return clientDTO;
	}
}
