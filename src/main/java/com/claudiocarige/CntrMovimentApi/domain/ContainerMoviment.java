package com.claudiocarige.CntrMovimentApi.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.claudiocarige.CntrMovimentApi.domain.enums.MovimentType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_container_moviment")
public class ContainerMoviment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "container_id")
	private Container container;
	@Enumerated(EnumType.ORDINAL)
	private MovimentType movimentType;

	@JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
	private LocalDateTime initialDate;
	@JsonFormat(pattern = "dd/MM/yyy HH:mm:ss")
	private LocalDateTime endDate;


	public ContainerMoviment() {
	}

	public ContainerMoviment(Long id, Container container, MovimentType movimentType, LocalDateTime initialDate,
			LocalDateTime endDate) {
		super();
		this.id = id;
		this.container = container;
		this.movimentType = movimentType;
		this.initialDate = initialDate;
		this.endDate = endDate;

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
