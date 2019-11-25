package com.pv.louvor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	
	@ManyToOne
    @JoinColumn(name = "gru_id")
	private Grupo grupo;
	
	@ManyToOne
    @JoinColumn(name = "cat_id")
	private Categoria categorias;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.musica")
	private Set<MusicaRepertorio> musicasRepertorio = new HashSet<>();
	
	@Column(name="mus_estudo")
	@Embedded
	private Estudo estudo;
	
	@Column(name="mus_tutorial")
	@Embedded
	private Tutorial tutorial;
	
	@Column(name="mus_dataInserida")
	private String dataInserida;

	
	@Column(name="mus_notaOriginal")
	private String notaOriginal;
	
	@Column(name="mus_notaTocada")
	private String notaTocada;	
	
	@Column(name="mus_notaDia")
	private String notaDia;
	
	@JsonIgnore
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "mus_igreja")
	private Igreja igreja;
	
	@Column(name="mus_sede")
	private boolean sede;
	
	public Musica () {
		
	}

	
	public Musica(Integer id, String nome,
			NotasMusicais notaOriginal, NotasMusicais notaTocada, boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.notaOriginal = notaOriginal.getDescricao();
		this.notaTocada = notaTocada.getDescricao();
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Categoria getCategorias() {
		return categorias;
	}


	public void setCategorias(Categoria categorias) {
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

	public String getNotaOriginal() {
		return notaOriginal;
	}

	public void setNotaOriginal(String notaOriginal) {
		this.notaOriginal = notaOriginal;
	}

	public String getNotaTocada() {
		return notaTocada;
	}

	public void setNotaTocada(String notaTocada) {
		this.notaTocada = notaTocada;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Igreja getIgreja() {
		return igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
	}
	
	public boolean isSede() {
		return sede;
	}


	public void setSede(boolean sede) {
		this.sede = sede;
	}
	
	public String getNotaDia() {
		return notaDia;
	}


	public void setNotaDia(String notaDia) {
		this.notaDia = notaDia;
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


	@Override
	public String toString() {
		return "Musica [id=" + id + ", nome=" + nome + ", grupo=" + grupo + ", categorias=" + categorias
				+ ", musicasRepertorio=" + musicasRepertorio + ", estudo=" + estudo + ", tutorial=" + tutorial
				+ ", dataInserida=" + dataInserida + ", notaOriginal=" + notaOriginal + ", notaTocada=" + notaTocada
				+ ", ativo=" + ativo + "]";
	}	
	
}
	
	