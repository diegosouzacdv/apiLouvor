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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.pv.louvor.model.dto.UsuarioDTO;

import javassist.expr.Instanceof;

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
	
	@OneToOne(cascade = CascadeType.ALL)
	private Equipe equipeDoDia;

	@OneToMany(mappedBy = "id.repertorio")
	private Set<MusicaRepertorio> musicasRepertorio = new HashSet<>();
	
	@Column(name="rep_criador")
	private String criador; 
	
	public Repertorio() {
		
	}

	public Repertorio(Integer id) {
		super();
		this.id = id;
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

	public double getTotalMusicas() {
		int quant = musicasRepertorio.size();
		return Integer.valueOf(quant);
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
		StringBuilder builder = new StringBuilder();
		builder.append("Repertorio número: ");
		builder.append(getId());
		builder.append("\n");
		builder.append("Data: ");
		builder.append(getData());
		builder.append("\n");
		builder.append("Equipe do Dia: ");
		builder.append("\n");
		builder.append("Ministros: ");
		builder.append(getEquipeDoDia().getMinistro());
		builder.append(" Instrumental: ");
		builder.append("Baterista: ");
		builder.append(getEquipeDoDia().getBaterista());
		builder.append(" Guitarrista: ");
		builder.append(getEquipeDoDia().getGuitarrista());
		builder.append(" Tecladista: ");
		builder.append(getEquipeDoDia().getTecladista());
		builder.append(" Violinista: ");
		builder.append(getEquipeDoDia().getViolonista());
		builder.append("\n");
		builder.append("Repertorio: ");
		builder.append("\n");
		for (MusicaRepertorio mr : getMusicasRepertorio()) {
			builder.append(mr.toString() + " ");
			builder.append("\n");
		}
		builder.append(" Total de Músicas: ");
		builder.append(getTotalMusicas());

		return builder.toString();
	}


}
