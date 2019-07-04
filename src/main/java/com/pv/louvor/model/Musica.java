package com.pv.louvor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Musica implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mus_id")
	private Integer id;
	
	@NotEmpty(message="Nome é obrigatório")
	@Length(min=5, max=80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	@Column(name="mus_nome")
	private String nome;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name= "MUSICA_GRUPO",
		joinColumns = @JoinColumn(name = "mus_id"),
		inverseJoinColumns = @JoinColumn(name = "gru_id"))
	private List<Grupo> grupo;
	
	@ManyToMany
	@JoinTable(name= "MUSICA_CATEGORIA",
		joinColumns = @JoinColumn(name = "mus_id"),
		inverseJoinColumns = @JoinColumn(name = "cat_id"))
	private List<Categoria> categorias;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.musica")
	private Set<MusicaRepertorio> musicasRepertorio = new HashSet<>();
	
	@Column(name="mus_estudo")
	@Embedded
	private Estudo estudo;
	
	@Column(name="mus_tutorial")
	@Embedded
	private Tutorial tutorial;
	
	@NotEmpty()
	@Column(name="mus_dataInserida")
	private String dataInserida;

	
	@Column(name="mus_notaOriginal")
	private Integer notaOriginal;
	
	@Column(name="mus_notaTocada")
	private Integer notaTocada;	
	
	private boolean ativo;
	
	public Musica() {
		
	}

	public Musica(Integer id, String nome,
			NotasMusicais notaOriginal, NotasMusicais notaTocada, boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.notaOriginal = notaOriginal.getCod();
		this.notaTocada = notaTocada.getCod();
		this.ativo = ativo;
	}
	
	@JsonIgnore
	public List<Repertorio> getRepertorios() {
		List<Repertorio> lista = new ArrayList<>();
		for (MusicaRepertorio x : musicasRepertorio) {
			lista.add(x.getRepertorio());
		}
		return lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Grupo> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<MusicaRepertorio> getMusicasRepertorio() {
		return musicasRepertorio;
	}

	public void setMusicasRepertorio(Set<MusicaRepertorio> musicasRepertorio) {
		this.musicasRepertorio = musicasRepertorio;
	}

	public Estudo getEstudo() {
		return estudo;
	}

	public void setEstudo(Estudo estudo) {
		this.estudo = estudo;
	}

	public Tutorial getTutorial() {
		return tutorial;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	public String getDataInserida() {
		return dataInserida;
	}

	public void setDataInserida(String dataInserida) {
		this.dataInserida = dataInserida;
	}

	public Integer getNotaOriginal() {
		return notaOriginal;
	}

	public void setNotaOriginal(Integer notaOriginal) {
		this.notaOriginal = notaOriginal;
	}

	public Integer getNotaTocada() {
		return notaTocada;
	}

	public void setNotaTocada(Integer notaTocada) {
		this.notaTocada = notaTocada;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
		Musica other = (Musica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
	
	