package com.loja.lojavirtual.repository.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraFilter {

	private Long clienteId;


	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacao;

}
