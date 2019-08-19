package com.pv.louvor.model.dto;

import java.io.Serializable;

public class Filtro implements Serializable{

	private static final long serialVersionUID = 1L;
	

	private String text;

	private String value;

	
	public Filtro() {
	}
	
	public Filtro(String obj) {
		text = obj;
		value = obj;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
