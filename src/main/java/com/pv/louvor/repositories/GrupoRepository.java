package com.pv.louvor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pv.louvor.model.Grupo;

@Repository
public interface GrupoRepository  extends JpaRepository<Grupo, Integer>{
	
}