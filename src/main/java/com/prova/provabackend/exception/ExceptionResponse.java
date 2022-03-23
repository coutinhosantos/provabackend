package com.prova.provabackend.exception;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExceptionResponse extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Date data;
	private String mensagem;
	private String descricao;

	public ExceptionResponse(Date data, String mensagem, String descricao) {
		this.data = data;
		this.mensagem = mensagem;
		this.descricao = descricao;
	}

}
