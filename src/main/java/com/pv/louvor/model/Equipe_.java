package com.pv.louvor.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Equipe.class)
public abstract class Equipe_ {

	public static volatile SingularAttribute<Equipe, String> baterista;
	public static volatile SingularAttribute<Equipe, String> tecladista;
	public static volatile ListAttribute<Equipe, String> ministro;
	public static volatile SingularAttribute<Equipe, String> guitarrista;
	public static volatile SingularAttribute<Equipe, Integer> id;
	public static volatile SingularAttribute<Equipe, String> violonista;

}

