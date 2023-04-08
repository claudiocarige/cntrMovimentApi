package com.claudiocarige.CntrMovimentApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.CntrMovimentApi.domain.Client;
import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerDTO;
import com.claudiocarige.CntrMovimentApi.repositories.ClientRepository;
import com.claudiocarige.CntrMovimentApi.repositories.ContainerRepository;
import com.claudiocarige.CntrMovimentApi.services.exception.DataIntegrityViolationException;
import com.claudiocarige.CntrMovimentApi.services.exception.NoSuchElementException;

@Service
public class ContainerService {

	@Autowired
	private ContainerRepository cntrRepository;
	
	@Autowired
	private ClientRepository clientRepository;

	public List<Container> findAll() {
		return cntrRepository.findAll();
	}

	public Container findById(Long id) {
		Optional<Container> cntr = cntrRepository.findById(id);
		return cntr.orElseThrow(() -> new NoSuchElementException("Container não encontrado!"));
	}
 
	public Container insert(ContainerDTO cntrDTO) {
		cntrDTO.setId(null);
		validateCntr(cntrDTO.getCntrNumber());
		Optional<Client> client = clientRepository.findById(cntrDTO.getClient().getId());
		Container newCntr = transformDTO(cntrDTO);
		newCntr.setClient(client.get());
		return cntrRepository.save(newCntr);
	}
  
	public void validateCntr(String cntrNumber) {
		Optional<Container> cntr = cntrRepository.findByCntrNumber(cntrNumber);
		if (!cntr.isEmpty() && cntr.get().getCntrNumber().equals(cntrNumber)) {
			throw new DataIntegrityViolationException("Container já existe na base de dados!");
		}
	}

	public Container transformDTO(ContainerDTO cntrDTO) {
		Container cntr = new Container();
		cntr.setId(cntrDTO.getId());
		cntr.setCntrNumber(cntrDTO.getCntrNumber());
		cntr.setStatusCntr(cntrDTO.getStatusCntr());
		cntr.setTypeCntr(cntrDTO.getTypeCntr());
		cntr.setCategoryCntr(cntrDTO.getCategoryCntr());
		cntr.setClient(cntrDTO.getClient());
		return cntr;
	}
}
