package com.pv.louvor.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
public class Tutorial implements Serializable{

private static final long serialVersionUID = 1L;
	
	@ElementCollection
	@CollectionTable(name="tut_violao")
	private List<String> violao;
	
	@ElementCollection
	@CollectionTable(name="tut_guitarra")
	private List<String> guitarra;
	
	@ElementCollection
	@CollectionTable(name="tut_baixo")
	private List<String> baixo;
	
	@ElementCollection
	@CollectionTable(name="tut_bateria")
	private List<String> bateria;
	
	@ElementCollection
	@CollectionTable(name="tut_teclado")
	private List<String> teclado;
	
	public Tutorial() {
		
	}

	public List<String> getViolao() {
		return violao;
	}

	public void setViolao(List<String> violao) {
		this.violao = violao;
	}

	public List<String> getGuitarra() {
		return guitarra;
	}

	public void setGuitarra(List<String> guitarra) {
		this.guitarra = guitarra;
	}

	public List<String> getBaixo() {
		return baixo;
	}

	public void setBaixo(List<String> baixo) {
		this.baixo = baixo;
	}

	public List<String> getBateria() {
		return bateria;
	}

	public void setBateria(List<String> bateria) {
		this.bateria = bateria;
	}

	public List<String> getTeclado() {
		return teclado;
	}

	public void setTeclado(List<String> teclado) {
		this.teclado = teclado;
	}

	@Override
	public String toString() {
		return "Tutorial [violao=" + violao + ", guitarra=" + guitarra + ", baixo=" + baixo + ", bateria=" + bateria
				+ ", teclado=" + teclado + "]";
	}
	
	
}
