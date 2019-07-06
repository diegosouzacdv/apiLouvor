package com.pv.louvor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.Usuario;

@Repository
public interface RepertorioRepository extends JpaRepository<Repertorio, Integer>{
	
}