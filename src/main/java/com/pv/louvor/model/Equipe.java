package com.pv.louvor.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Equipe implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "equ_id")
	@JsonIgnore
	private Integer id;
	
	
	
	@ElementCollection
	private List<String> ministro;
	@ElementCollection
	private List<String> violonista;
	@ElementCollection
	private List<String> guitarrista;
	@ElementCollection
	private List<String> baterista;
	@ElementCollection
	private List<String> tecladista;
	@ElementCollection
	private List<String> baixista;
	
	public Equipe() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getMinistro() {
		return ministro;
	}

	public void setMinistro(List<String> ministro) {
		this.ministro = ministro;
	}

	public List<String> getViolonista() {
		return violonista;
	}

	public void setViolonista(List<String> violonista) {
		this.violonista = violonista;
	}

	public List<String> getGuitarrista() {
		return guitarrista;
	}

	public void setGuitarrista(List<String> guitarrista) {
		this.guitarrista = guitarrista;
	}

	public List<String> getBaterista() {
		return baterista;
	}

	public void setBaterista(List<String> baterista) {
		this.baterista = baterista;
	}

	public List<String> getTecladista() {
		return tecladista;
	}

	public void setTecladista(List<String> tecladista) {
		this.tecladista = tecladista;
	}

	public List<String> getBaixista() {
		return baixista;
	}

	public void setBaixista(List<String> baixista) {
		this.baixista = baixista;
	}
	
	
}
