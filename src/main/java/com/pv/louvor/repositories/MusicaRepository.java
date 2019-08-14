package com.pv.louvor.repositories;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pv.louvor.model.Categoria;
import com.pv.louvor.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer>{

	@Transactional(readOnly = true)
	Musica findByNome(String nome);
	
	/*@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Musica obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Musica> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	@Transactional(readOnly = true)
	Page<Musica> findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(String nome,Categoria categorias, Pageable pageRequest);*/
	@Transactional(readOnly = true)
	Page<Musica> findDistinctByNomeIgnoreCaseContainingAndAtivoIs(String nome, boolean ativo, Pageable pageRequest);
}