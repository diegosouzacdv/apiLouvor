package com.pv.louvor.repositories.repertorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Funcao_;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Igreja_;
import com.pv.louvor.model.Musica;
import com.pv.louvor.model.Musica_;
import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.Usuario_;

public class RepertorioRepositoryImpl implements RepertorioRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;

	
	/**
	 *Essa consulta retorna os usuarios que tem determinada funcao.
	 */
	@Override
	public List<Usuario> funcoes(Funcao funcao, Igreja igreja) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		
		Join<Usuario, Funcao> join = root.join("funcao", JoinType.INNER);
		//criar restrições
		Predicate[] predicates = criarRestricoes(join, builder, funcao, root, igreja);
		criteria.where(predicates);
		TypedQuery<Usuario> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(Join<Usuario, Funcao> join, CriteriaBuilder builder, Funcao funcao, Root<Usuario> root, Igreja igreja) {
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(join.get(Funcao_.id), funcao.getId()));
		predicates.add(builder.equal(root.get(Usuario_.igreja).get(Igreja_.id), igreja.getId()));
		return predicates.toArray(new Predicate[predicates.size()]);
	}	
}
