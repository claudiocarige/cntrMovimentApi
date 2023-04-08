package com.claudiocarige.CntrMovimentApi.domain.dtos;

import java.time.LocalDateTime;

import com.claudiocarige.CntrMovimentApi.domain.Container;
import com.claudiocarige.CntrMovimentApi.domain.ContainerMoviment;
import com.claudiocarige.CntrMovimentApi.domain.enums.MovimentType;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ContainerMovimentDTO {

	private Long id;
	private Container container;
	private MovimentType movimentType;
	@JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
	private LocalDateTime initialDate;
	@JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
	private LocalDateTime endDate;

	public ContainerMovimentDTO() {
	}

	public ContainerMovimentDTO(ContainerMoviment cntrMove) {
		super();
		this.id = cntrMove.getId();
		this.container = cntrMove.getContainer();
		this.movimentType = cntrMove.getMovimentType();
		this.initialDate = cntrMove.getInitialDate();
		this.endDate = cntrMove.getEndDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public MovimentType getMovimentType() {
		return movimentType;
	}

	public void setMovimentType(MovimentType movimentType) {
		this.movimentType = movimentType;
	}

	public LocalDateTime getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(LocalDateTime initialDate) {
		this.initialDate = initialDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

}
