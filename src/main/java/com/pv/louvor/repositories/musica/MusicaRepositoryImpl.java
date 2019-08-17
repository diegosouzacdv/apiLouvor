package com.pv.louvor.repositories.musica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.pv.louvor.model.Categoria_;
import com.pv.louvor.model.Grupo_;
import com.pv.louvor.model.Musica;
import com.pv.louvor.model.Musica_;
import com.pv.louvor.model.dto.MusicaDTO;

public class MusicaRepositoryImpl implements MusicaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	@Override
	public List<Musica> filtrar(MusicaDTO musicaDto) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Musica> criteria = builder.createQuery(Musica.class);
		Root<Musica> root = criteria.from(Musica.class);
		
		//criar restrições
		Predicate[] predicates = criarRestricoes(musicaDto, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Musica> query = manager.createQuery(criteria);		
		return query.getResultList();
	}
	private Predicate[] criarRestricoes(MusicaDTO musicaDto, CriteriaBuilder builder, Root<Musica> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(musicaDto.getNome())) {
			predicates.add(builder.like(
				builder.lower(root.get(Musica_.nome)),"%" + musicaDto.getNome().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(musicaDto.getGrupo())) {
			predicates.add(builder.like(
				builder.lower(root.get(Musica_.grupo).get(Grupo_.nome)),"%" + musicaDto.getGrupo().toLowerCase() + "%"));
		}
		
		if(musicaDto.getCategoria() != null) {
			predicates.add(builder.like(
					builder.lower(root.get(Musica_.categorias).get(Categoria_.nome)),"%" + musicaDto.getGrupo().toLowerCase() + "%"));
		}
		
		if(!musicaDto.isAtivo()) {
			predicates.add(builder.equal(root.get(Musica_.ativo), false));
		}
		
		if(musicaDto.isAtivo()) {
			predicates.add(builder.equal(root.get(Musica_.ativo), true));
		}
		
		if(!StringUtils.isEmpty(musicaDto.getData())) {
			predicates.add(builder.like(
				builder.lower(root.get(Musica_.dataInserida)),"%" + musicaDto.getData().toLowerCase() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
