package com.prova.provabackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.prova.provabackend.vo.AlunoVO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Aluno implements Serializable{
	
	private static final long serialVersionUID = 3091097963380235067L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "idade", nullable = false)
	private Integer idade;
	
	public static Aluno mapper(AlunoVO alunoVO) {
		return new ModelMapper().map(alunoVO, Aluno.class);
	}
}
