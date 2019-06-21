package com.pv.louvor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pv.louvor.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer>{
	
}