package com.claudiocarige.CntrMovimentApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.CntrMovimentApi.domain.Client;
import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerDTO;
import com.claudiocarige.CntrMovimentApi.domain.enums.CategoryCntr;
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

	public Container update(ContainerDTO cntrDTO, Long id) {
		cntrDTO.setId(id);
		validateCntr(cntrDTO);
		Container oldCntr = transformDTO(cntrDTO);
		return cntrRepository.save(oldCntr);
	}

	public Container updateClientCntr(Long id, ContainerDTO cntrDTO) {
		cntrDTO.setId(id);
		Container oldCntr = findById(cntrDTO.getId());
		if (!cntrDTO.getCntrNumber().equals(oldCntr.getCntrNumber())) {
			throw new DataIntegrityViolationException("O número do Container não corresponde ao ID informado!");
		}
		Client newClient = cntrDTO.getClient();
		newClient = clientRepository.save(newClient);
		cntrDTO.setClient(newClient);
		Container cntr = transformDTO(cntrDTO);
		return cntrRepository.save(cntr);
	}
	
	public List<Container> findByCategory(Integer id) {
		CategoryCntr category =  CategoryCntr.toEnum(id);
		return cntrRepository.findByCategory(category);
	}

	public void validateCntr(ContainerDTO cntrDTO) {
		Optional<Container> oldCntr = cntrRepository.findById(cntrDTO.getId());
		if (!oldCntr.get().getCntrNumber().equals(cntrDTO.getCntrNumber())) {
			throw new DataIntegrityViolationException("O número do Container não pode ser alterado!");
		} else if (!oldCntr.get().getClient().getCnpj().equals(cntrDTO.getClient().getCnpj())) {
			throw new DataIntegrityViolationException("O Cliente não pode ser alterado nesse endpoint!");
		}
	}

	public void validateCntr(String cntrNumber) {
		Optional<Container> cntr = cntrRepository.findByCntrNumber(cntrNumber);
		if (cntr.isPresent() && cntr.get().getCntrNumber().equals(cntrNumber)) {
			throw new DataIntegrityViolationException("Container já existe na base de dados!");
		}
	}

	public ContainerDTO formatCntr(ContainerDTO cntrDTO) {
		cntrDTO.setCntrNumber(cntrDTO.getCntrNumber().replaceAll("[^a-zA-Z0-9]+", ""));
		if (!cntrDTO.getCntrNumber().matches("[A-Z]{4}[0-9]{7}")) {
			throw new DataIntegrityViolationException("Numero do CNTR deve conter 4 letras MAIÚSCULAS e 7 Numeros!");
		}
		return cntrDTO;
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
