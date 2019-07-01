package com.pv.louvor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pv.louvor.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	@Transactional(readOnly = true)
	Categoria findByNome(String nome);
	
}
