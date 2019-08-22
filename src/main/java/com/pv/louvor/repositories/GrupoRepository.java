package com.pv.louvor.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pv.louvor.model.Grupo;

@Repository
public interface GrupoRepository  extends JpaRepository<Grupo, Integer>{

	@Transactional(readOnly = true)
	Grupo findByNome(String nome);
	
	@Transactional(readOnly = true)
	Page<Grupo> findDistinctByNomeIgnoreCaseContainingAndAtivoIs(String nome, boolean ativo, Pageable pageRequest);
	
}