package com.pv.louvor.model.dto;


import java.io.Serializable;

import com.pv.louvor.model.Repertorio;

public class RepertorioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String data;
	
	public RepertorioDTO() {
		
	}
	
	public RepertorioDTO(Repertorio obj) {
		id = obj.getId();
		data = obj.getData();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
