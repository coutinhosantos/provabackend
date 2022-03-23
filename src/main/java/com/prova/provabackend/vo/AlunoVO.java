package com.prova.provabackend.vo;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.prova.provabackend.model.Aluno;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","nome","idade"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AlunoVO extends RepresentationModel<AlunoVO> implements Serializable{

	private static final long serialVersionUID = -107701458196347664L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	@NotEmpty(message = "O campo nome é obrigatório")
	@NotNull(message = "O campo nome é obrigatório")
	private String nome;
	
	@JsonProperty("idade")
	@NotEmpty(message = "O campo idade é obrigatório")
	@NotNull(message = "O campo idade é obrigatório")
	private Integer idade;
	
	public static AlunoVO mapper(Aluno aluno) {
		return new ModelMapper().map(aluno, AlunoVO.class);
	}
}
