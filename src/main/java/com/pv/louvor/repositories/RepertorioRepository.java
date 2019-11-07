package com.pv.louvor.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pv.louvor.model.Repertorio;
import com.pv.louvor.repositories.repertorio.RepertorioRepositoryQuery;

@Repository
public interface RepertorioRepository extends JpaRepository<Repertorio, Integer>, RepertorioRepositoryQuery{
	List<Repertorio> findDistinctByAtivoIsAndIgrejaId(boolean ativo, Integer igreja);
	
	List<Repertorio> findDistinctByAtivoIs(boolean ativo);
	
	@Transactional(readOnly = true)
	Page<Repertorio> findDistinctByDataIgnoreCaseContaining(String data, Pageable pageRequest);
}