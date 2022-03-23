package com.prova.provabackend.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prova.provabackend.service.AlunoService;
import com.prova.provabackend.vo.AlunoVO;

import lombok.var;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	private AlunoService alunoService;
	private PagedResourcesAssembler<AlunoVO> assembler;

	@Autowired
	public AlunoController(AlunoService alunoService, PagedResourcesAssembler<AlunoVO> assembler) {
		super();
		this.alunoService = alunoService;
		this.assembler = assembler;
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public AlunoVO buscaAluno(@PathVariable("id") Long id) {
		AlunoVO alunoVO = alunoService.findById(id);
		alunoVO.add(linkTo(methodOn(AlunoController.class).buscaAluno(id)).withSelfRel());
		return alunoVO;
	}

	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> buscaAlunos(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "order", defaultValue = "asc") String order) {

		var sortOrder = "desc".equalsIgnoreCase(order) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortOrder, "id"));

		Page<AlunoVO> alunos = alunoService.findAll(pageable);
		alunos.stream().forEach(a -> a.add(linkTo(methodOn(AlunoController.class).buscaAluno(a.getId())).withSelfRel()));
		PagedModel<EntityModel<AlunoVO>> pagedModel = assembler.toModel(alunos);

		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}

	@PostMapping(produces = { "application/json" }, consumes = { "application/json", "application/xml",	"application/x-yaml" })
	public AlunoVO create(@RequestBody AlunoVO alunoVO) {
		AlunoVO vo = alunoService.create(alunoVO);
		vo.add(linkTo(methodOn(AlunoController.class).buscaAluno(alunoVO.getId())).withSelfRel());

		return vo;
	}

	@PutMapping(produces = { "application/json" }, consumes = { "application/json", "application/xml",	"application/x-yaml" })
	public AlunoVO update(@RequestBody AlunoVO alunoVO) {
		AlunoVO vo = alunoService.update(alunoVO);
		vo.add(linkTo(methodOn(AlunoController.class).buscaAluno(alunoVO.getId())).withSelfRel());
		return vo;
	}

	@DeleteMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		alunoService.delete(id);
		return ResponseEntity.ok().build();
	}
}
