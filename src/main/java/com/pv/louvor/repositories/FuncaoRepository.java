package com.pv.louvor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pv.louvor.model.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Integer>{

	@Transactional(readOnly = true)
	Funcao findByNome(String nome);
	
}