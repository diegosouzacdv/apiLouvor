package com.pv.louvor.repositories.musica;

import java.util.List;

import com.pv.louvor.model.Musica;
import com.pv.louvor.model.dto.MusicaDTO;

public interface MusicaRepositoryQuery {

	public List<Musica> filtrar (MusicaDTO musicaDto);
}
