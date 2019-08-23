package com.pv.louvor.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Repertorio.class)
public abstract class Repertorio_ {

	public static volatile SingularAttribute<Repertorio, Equipe> equipeDoDia;
	public static volatile SingularAttribute<Repertorio, Boolean> ativo;
	public static volatile SingularAttribute<Repertorio, String> data;
	public static volatile SingularAttribute<Repertorio, String> dataSemana;
	public static volatile SetAttribute<Repertorio, MusicaRepertorio> musicasRepertorio;
	public static volatile SingularAttribute<Repertorio, String> criador;
	public static volatile SingularAttribute<Repertorio, Integer> id;

}

