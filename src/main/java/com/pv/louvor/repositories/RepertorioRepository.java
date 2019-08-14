package com.pv.louvor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pv.louvor.model.Repertorio;

@Repository
public interface RepertorioRepository extends JpaRepository<Repertorio, Integer>{
	List<Repertorio> findDistinctByAtivoIs(boolean ativo);
}