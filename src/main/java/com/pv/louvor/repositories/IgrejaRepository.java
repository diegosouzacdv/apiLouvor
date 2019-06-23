package com.pv.louvor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pv.louvor.model.Igreja;

@Repository
public interface IgrejaRepository extends JpaRepository<Igreja, Integer>{

	Igreja findByNome(String nome);
	
}
