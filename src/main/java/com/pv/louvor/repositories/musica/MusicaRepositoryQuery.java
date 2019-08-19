package com.pv.louvor.repositories.musica;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pv.louvor.model.Musica;
import com.pv.louvor.model.dto.Filtro;
import com.pv.louvor.model.dto.MusicaDTO;

public interface MusicaRepositoryQuery {

	public Page<Musica> filtrar (MusicaDTO musicaDto, Pageable pageable);
	
	public List<Filtro> anosMusica();

	public List<Filtro> gruposMusica();

	public List<Filtro> categoriasMusica();
}
