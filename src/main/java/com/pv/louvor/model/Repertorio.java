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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Repertorio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rep_id")
	private Integer id;
	
	@NotEmpty()
	@Column(name="rep_data")
	private String data;
	
	@Column(name="rep_data_semana")
	private String dataSemana;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Equipe equipeDoDia;

	@NotNull
	@OneToMany(mappedBy = "id.repertorio", cascade = CascadeType.ALL)
	private Set<MusicaRepertorio> musicasRepertorio = new HashSet<>();
	
	@Column(name="rep_criador")
	private String criador; 
	
	@Column(name="rep_ativo")
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "rep_igreja")
	private Igreja igreja;
	
	@Column(name="rep_observacao", length=5012)
	private String observacao;
	
	public Repertorio() {
		
	}

	public Repertorio(Integer id) {
		super();
		this.id = id;
	}
	
	public void deleteMusicas() {
		musicasRepertorio.clear();
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
	
	public String getCriador() {
		return criador;
	}

	public void setCriador(String criador) {
		this.criador = criador;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public double getTotalMusicas() {
		int quant = musicasRepertorio.size();
		return Integer.valueOf(quant);
	}
	
	public String getDataSemana() {
		return dataSemana;
	}

	public void setDataSemana(String dataSemana) {
		this.dataSemana = dataSemana;
	}

	public Igreja getIgreja() {
		return igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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

	@Override
	public String toString() {
		return "Repertorio [id=" + id + ", data=" + data + ", dataSemana=" + dataSemana + ", equipeDoDia=" + equipeDoDia
				+ ", musicasRepertorio=" + musicasRepertorio + ", criador=" + criador + ", ativo=" + ativo + ", igreja="
				+ igreja + ", observacao=" + observacao + ", getId()=" + getId() + ", getData()=" + getData()
				+ ", getEquipeDoDia()=" + getEquipeDoDia() + ", getMusicasRepertorio()=" + getMusicasRepertorio()
				+ ", getCriador()=" + getCriador() + ", isAtivo()=" + isAtivo() + ", getTotalMusicas()="
				+ getTotalMusicas() + ", getDataSemana()=" + getDataSemana() + ", getIgreja()=" + getIgreja()
				+ ", getObservacao()=" + getObservacao() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}




}
