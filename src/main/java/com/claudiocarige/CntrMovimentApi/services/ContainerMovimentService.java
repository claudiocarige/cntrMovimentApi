package com.claudiocarige.CntrMovimentApi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.CntrMovimentApi.domain.ContainerMoviment;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerDTO;
import com.claudiocarige.CntrMovimentApi.domain.dtos.ContainerMovimentDTO;
import com.claudiocarige.CntrMovimentApi.domain.enums.MovimentType;
import com.claudiocarige.CntrMovimentApi.repositories.ContainerMovimentRepository;
import com.claudiocarige.CntrMovimentApi.services.exception.DataIntegrityViolationException;
import com.claudiocarige.CntrMovimentApi.services.exception.NoSuchElementException;

@Service
public class ContainerMovimentService {

	@Autowired
	private ContainerMovimentRepository cntrMoveRepository;

	@Autowired
	private ContainerService cntrService;

	public List<ContainerMoviment> findAll() {
		return cntrMoveRepository.findAll();
	}

	public ContainerMoviment findById(Long id) {
		Optional<ContainerMoviment> cntr = cntrMoveRepository.findById(id);
		return cntr.orElseThrow(() -> new NoSuchElementException("Movimentação não encontrada!"));
	}

	public ContainerMoviment insert(ContainerMovimentDTO cntrMoveDTO) {
		cntrMoveDTO.setId(null);
		ContainerMoviment newCntr = transformDTO(cntrMoveDTO);
		return cntrMoveRepository.save(newCntr);
	}

	public ContainerMoviment update(Long id, ContainerMovimentDTO cntrMoveDTO) {
		cntrMoveDTO.setId(id);
		ContainerMoviment oldCntrMove = findById(cntrMoveDTO.getId());
		if (!cntrMoveDTO.getContainer().getCntrNumber().equals(oldCntrMove.getContainer().getCntrNumber())
				&& !cntrMoveDTO.getContainer().getClient().getCnpj()
						.equals(oldCntrMove.getContainer().getClient().getCnpj())) {
 
			throw new DataIntegrityViolationException(
					"O numero do Container e/ou o CNPJ do cliente não podem ser Alterados");
		}
		oldCntrMove = transformDTO(cntrMoveDTO);
		oldCntrMove.setContainer(cntrService.updateClientCntr(cntrMoveDTO.getContainer().getId(),
				new ContainerDTO(cntrMoveDTO.getContainer())));
		return cntrMoveRepository.save(oldCntrMove);
	} 
 
	public ContainerMoviment transformDTO(ContainerMovimentDTO cntrMoveDTO) {
		ContainerMoviment cntr = new ContainerMoviment();
		cntr.setId(cntrMoveDTO.getId());
		cntr.setContainer(cntrMoveDTO.getContainer());
		cntr.setMovimentType(cntrMoveDTO.getMovimentType());
		cntr.setInitialDate(cntrMoveDTO.getInitialDate());
		cntr.setEndDate(cntrMoveDTO.getEndDate());
		return cntr;
	}
}
