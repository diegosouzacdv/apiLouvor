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
	
	private String violonista;
	
	private String guitarrista;
	
	private String baterista;
	
	private String tecladista;
	
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

	public String getViolonista() {
		return violonista;
	}

	public void setViolonista(String violonista) {
		this.violonista = violonista;
	}

	public String getGuitarrista() {
		return guitarrista;
	}

	public void setGuitarrista(String guitarrista) {
		this.guitarrista = guitarrista;
	}

	public String getBaterista() {
		return baterista;
	}

	public void setBaterista(String baterista) {
		this.baterista = baterista;
	}

	public String getTecladista() {
		return tecladista;
	}

	public void setTecladista(String tecladista) {
		this.tecladista = tecladista;
	}

	
}
