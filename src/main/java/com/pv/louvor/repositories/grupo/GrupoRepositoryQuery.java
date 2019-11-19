package com.pv.louvor.repositories.grupo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pv.louvor.model.Grupo;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.dto.GrupoDTO;

public interface GrupoRepositoryQuery {

	Page<Grupo> filtrar(GrupoDTO grupo, Igreja local, Igreja sede, Pageable pageable);


}
