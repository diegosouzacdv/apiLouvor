package com.pv.louvor.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.UsuarioSemFuncoesDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Transactional(readOnly = true)
	Usuario findByEmail(String email);

	UsuarioSemFuncoesDTO save(UsuarioSemFuncoesDTO obj);

	Usuario findById(Integer id);

	List<Usuario> findByAtivoAndIgrejaId(boolean ativo, Integer id);
}
