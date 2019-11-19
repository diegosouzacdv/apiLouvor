package com.pv.louvor.repositories.grupo;

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
import com.pv.louvor.model.Grupo;
import com.pv.louvor.model.Grupo_;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Musica_;
import com.pv.louvor.model.dto.GrupoDTO;

public class GrupoRepositoryImpl implements GrupoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	@Override
	public Page<Grupo> filtrar(GrupoDTO grupo, Igreja local, Igreja sede, Pageable pageable) {
		
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Grupo> criteria = builder.createQuery(Grupo.class);
		Root<Grupo> root = criteria.from(Grupo.class);
		
		
		Order order = builder.desc(root.get(Grupo_.id));
		criteria.orderBy(order);
		
		//criar restrições
		Predicate[] predicates = criarRestricoes(grupo, local, sede, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Grupo> query = manager.createQuery(criteria);
		
		adicionarRestricoesdePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(grupo));
	}

	private Predicate[] criarRestricoes(GrupoDTO grupo, Igreja local, Igreja sede, CriteriaBuilder builder, Root<Grupo> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(grupo.getNome())) {
			predicates.add(builder.like(
				builder.lower(root.get(Grupo_.nome)),"%" + grupo.getNome().toLowerCase() + "%"));
		}
		
		if(local != null) {
			predicates.add(builder.or(builder.equal(root.get("igreja"),local), builder.equal(root.get("sede"), true)));
		}
	
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesdePaginacao(TypedQuery<Grupo> query, Pageable pageable) {
		int paginaAtual  = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		}
	
	private Long total(GrupoDTO grupo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Grupo> root = criteria.from(Grupo.class);
		
		Predicate[] predicates = criarRestricoes(grupo, null, null, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
