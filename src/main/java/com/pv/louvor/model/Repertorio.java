package com.pv.louvor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repertorio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rep_id")
	private Integer id;
	
	@Column(name="rep_data")
	private String data;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Equipe equipeDoDia;

	@OneToMany(mappedBy = "id.repertorio")
	private Set<MusicaRepertorio> musicasRepertorio = new HashSet<>();
	
//	@ManyToMany()
//	@JoinTable(name= "REPERTORIO_MUSICA",
//		joinColumns = @JoinColumn(name = "rep_id"),
//		inverseJoinColumns = @JoinColumn(name = "mus_id"))
//	private List<Musica> musica;
		
	
	public Repertorio() {
		
	}

	public Repertorio(Integer id, String data) {
		super();
		this.id = id;
		this.data = data;
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

	public Equipe getEquipeDoDia() {
		return equipeDoDia;
	}

	public void setEquipeDoDia(Equipe equipeDoDia) {
		this.equipeDoDia = equipeDoDia;
	}

	public Set<MusicaRepertorio> getMusicasRepertorio() {
		return musicasRepertorio;
	}

	public void setMusicasRepertorio(Set<MusicaRepertorio> musicasRepertorio) {
		this.musicasRepertorio = musicasRepertorio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repertorio other = (Repertorio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
