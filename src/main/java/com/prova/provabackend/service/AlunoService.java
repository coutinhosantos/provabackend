package com.prova.provabackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prova.provabackend.model.Aluno;
import com.prova.provabackend.repository.AlunoRepository;
import com.prova.provabackend.vo.AlunoVO;

import lombok.var;

@Service
public class AlunoService {

	private AlunoRepository alunoRepository;

	@Autowired
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	public AlunoVO create(AlunoVO alunoVO) {
		return AlunoVO.mapper(alunoRepository.save(Aluno.mapper(alunoVO)));
	}

	public Page<AlunoVO> findAll(Pageable pageable){
		var page = alunoRepository.findAll(pageable);
		return page.map(this::convert);
	}
	
	private AlunoVO convert(Aluno aluno) {
		return AlunoVO.mapper(aluno);
	}
	
	public AlunoVO findById(Long id) {
		return AlunoVO.mapper(alunoRepository.getById(id));
	}
	
	public AlunoVO update(AlunoVO alunoVO) {
		final Optional<Aluno> optional = alunoRepository.findById(alunoVO.getId());
		
		if(!optional.isPresent()) {
			new Exception("Aluno não existe");
			alunoVO = null;
		}
		
		return AlunoVO.mapper(alunoRepository.save(Aluno.mapper(alunoVO)));
	}
	
	public void delete(Long id) {
		final Optional<Aluno> optional = alunoRepository.findById(id);
		
		if(!optional.isPresent()) {
			new Exception("Aluno não existe");
		}
		
		alunoRepository.deleteById(id);
	}
}
