package com.pv.louvor.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Musica.class)
public abstract class Musica_ {

	public static volatile SingularAttribute<Musica, Boolean> ativo;
	public static volatile SingularAttribute<Musica, String> dataInserida;
	public static volatile SingularAttribute<Musica, String> notaTocada;
	public static volatile SetAttribute<Musica, MusicaRepertorio> musicasRepertorio;
	public static volatile SingularAttribute<Musica, Categoria> categorias;
	public static volatile SingularAttribute<Musica, Grupo> grupo;
	public static volatile SingularAttribute<Musica, String> nome;
	public static volatile SingularAttribute<Musica, Integer> id;
	public static volatile SingularAttribute<Musica, Tutorial> tutorial;
	public static volatile SingularAttribute<Musica, Estudo> estudo;
	public static volatile SingularAttribute<Musica, String> notaOriginal;

}

