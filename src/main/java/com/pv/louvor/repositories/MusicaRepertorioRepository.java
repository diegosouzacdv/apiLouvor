package com.pv.louvor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pv.louvor.model.MusicaRepertorio;

@Repository
public interface MusicaRepertorioRepository extends JpaRepository<MusicaRepertorio, Integer>{
	
}
