package com.claudiocarige.CntrMovimentApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.CntrMovimentApi.domain.Client;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ClientDTO;
import com.claudiocarige.CntrMovimentApi.repositories.ClientRepository;
import com.claudiocarige.CntrMovimentApi.services.exception.DataIntegrityViolationException;
import com.claudiocarige.CntrMovimentApi.services.exception.NoSuchElementException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Client findById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.orElseThrow(() -> new NoSuchElementException("Cliente não encontrado!"));
	}

	public Client insert(ClientDTO clientDTO) {
		clientDTO.setId(null);
		cnpjValidate(clientDTO);
		Client newClient = transfClientDTO(clientDTO);
		return clientRepository.save(newClient);
	}

	public Client update(Long id,ClientDTO clientDTO) {
		clientDTO.setId(id);
		Client client = findById(id);
		if(!clientDTO.getCnpj().equals(client.getCnpj())) {
			throw new DataIntegrityViolationException("O CNPJ não pode ser alterado!");
		}
		client = transfClientDTO(clientDTO);
		return clientRepository.save(client);
	}
	public void cnpjValidate(ClientDTO clientDTO) {
		Optional<Client> client = clientRepository.findByCnpj(clientDTO.getCnpj());
		if (client.isPresent() && client.get().getCnpj().equals(clientDTO.getCnpj())) {
			throw new DataIntegrityViolationException("CNPJ já cadastrado no sistema");
		}
	}

	private Client transfClientDTO(ClientDTO clientDTO) {
		Client client = new Client();
		client.setId(clientDTO.getId());
		client.setName(clientDTO.getName());
		client.setCnpj(clientDTO.getCnpj());
		client.setContact(clientDTO.getContact());
		client.setEmail(clientDTO.getEmail());
		return client;
	}


}
