package com.pv.louvor.repositories.musica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import com.pv.louvor.model.Categoria_;
import com.pv.louvor.model.Grupo_;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Musica;
import com.pv.louvor.model.Musica_;
import com.pv.louvor.model.dto.Filtro;
import com.pv.louvor.model.dto.MusicaDTO;

public class MusicaRepositoryImpl implements MusicaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	@Override
	public Page<Musica> filtrar(MusicaDTO musicaDto, Igreja igreja, Igreja sede, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Musica> criteria = builder.createQuery(Musica.class);
		Root<Musica> root = criteria.from(Musica.class);
		
		Order order = builder.desc(root.get(Musica_.id));
		criteria.orderBy(order);
		
		//criar restrições
		Predicate[] predicates = criarRestricoes(musicaDto, igreja, sede, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Musica> query = manager.createQuery(criteria);
		
		adicionarRestricoesdePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(musicaDto, igreja, sede));
	}

	private Predicate[] criarRestricoes(MusicaDTO musicaDto, Igreja igreja, Igreja sede, CriteriaBuilder builder, Root<Musica> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(musicaDto.getNome())) {
			predicates.add(builder.like(
				builder.lower(root.get(Musica_.nome)),"%" + musicaDto.getNome().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(musicaDto.getGrupo())) {
			predicates.add(builder.like(
				builder.lower(root.get(Musica_.grupo).get(Grupo_.nome)),musicaDto.getGrupo().toLowerCase()));
		}
		
		if(!StringUtils.isEmpty(musicaDto.getCategoria())) {
			predicates.add(builder.like(
					builder.lower(root.get(Musica_.categorias).get(Categoria_.nome)),musicaDto.getCategoria().toLowerCase()));
		}
		
		if(!musicaDto.isAtivo()) {
			predicates.add(builder.equal(root.get(Musica_.ativo), false));
		}
		
		if(musicaDto.isAtivo()) {
			predicates.add(builder.equal(root.get(Musica_.ativo), true));
		}
		
		if(!StringUtils.isEmpty(musicaDto.getData())) {
			predicates.add(builder.like(
				builder.lower(root.get(Musica_.dataInserida)),musicaDto.getData().toLowerCase()));
		}
		
		if(igreja != null) {
			predicates.add(builder.or(builder.equal(root.get("igreja"),igreja), builder.equal(root.get("sede"), true)));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesdePaginacao(TypedQuery<Musica> query, Pageable pageable) {
		int paginaAtual  = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		}
	
	private Long total(MusicaDTO musicaDto, Igreja igreja, Igreja sede) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Musica> root = criteria.from(Musica.class);
		
		Predicate[] predicates = criarRestricoes(musicaDto, igreja, sede, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	/*@Override Exemplo de consulta que traz somente um campo
	public List<Musica> anosMusica() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<Musica> root = criteria.from(Musica.class);
		criteria.select(root.get(Musica_.dataInserida));
		
		TypedQuery<String> query = manager.createQuery(criteria);
		return List<String> anos = query.getResultList();
	}*/
	
	@Override
	public List<Filtro> anosMusica(Igreja igreja) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Filtro> criteria = builder.createQuery(Filtro.class);
		Root<Musica> root = criteria.from(Musica.class);
		criteria.distinct(true);
		criteria.multiselect(root.get(Musica_.dataInserida));
		criteria.where(builder.or(builder.equal(root.get("igreja"),igreja), builder.equal(root.get("sede"), true)));
		TypedQuery<Filtro> query = manager.createQuery(criteria);
		List<Filtro> anos = query.getResultList();
		return anos;
	}
	
	@Override
	public List<Filtro> gruposMusica(Igreja igreja) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Filtro> criteria = builder.createQuery(Filtro.class);
		Root<Musica> root = criteria.from(Musica.class);
		criteria.distinct(true);
		criteria.multiselect(root.get(Musica_.grupo).get(Grupo_.nome));
		criteria.where(builder.or(builder.equal(root.get("igreja"),igreja), builder.equal(root.get("sede"), true)));
		
		TypedQuery<Filtro> query = manager.createQuery(criteria);
		List<Filtro> grupos = query.getResultList();
		return grupos;
	}
	
	@Override
	public List<Filtro> categoriasMusica(Igreja igreja) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Filtro> criteria = builder.createQuery(Filtro.class);
		Root<Musica> root = criteria.from(Musica.class);
		criteria.distinct(true);
		criteria.multiselect(root.get(Musica_.categorias).get(Categoria_.nome));
		criteria.where(builder.or(builder.equal(root.get("igreja"),igreja), builder.equal(root.get("sede"), true)));
		TypedQuery<Filtro> query = manager.createQuery(criteria);
		List<Filtro> categorias = query.getResultList();
		return categorias;
	}
}
