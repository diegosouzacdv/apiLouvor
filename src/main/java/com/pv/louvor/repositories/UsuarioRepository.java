package com.pv.louvor.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.UsuarioSemFuncoesDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Transactional(readOnly = true)
	Usuario findByEmail(String email);

	UsuarioSemFuncoesDTO save(UsuarioSemFuncoesDTO obj);
	
	@Query("UPDATE Usuario u set u.perfis = NULL where u.id = ?1")
	Usuario apagarPerfil(int id);

	Usuario findById(Integer id);
	
	List<Usuario> findByAtivo(boolean ativo);
}
