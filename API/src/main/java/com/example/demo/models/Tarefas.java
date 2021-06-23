package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "tarefas_tb")
public class Tarefas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nomeTarefa;
	private String tituloTarefa;
	private String descricaoTarefa;
	private Boolean completada;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	public Tarefas() {
		super();
	}

	public Tarefas(Long id, String nomeTarefa, String tituloTarefa, String descricaoTarefa, Boolean completada) {
		super();
		this.id = id;
		this.nomeTarefa = nomeTarefa;
		this.tituloTarefa = tituloTarefa;
		this.descricaoTarefa = descricaoTarefa;
		this.completada = completada;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeTarefa() {
		return nomeTarefa;
	}

	public void setNomeTarefa(String nomeTarefa) {
		this.nomeTarefa = nomeTarefa;
	}

	public String getTituloTarefa() {
		return tituloTarefa;
	}

	public void setTituloTarefa(String tituloTarefa) {
		this.tituloTarefa = tituloTarefa;
	}

	public String getDescricaoTarefa() {
		return descricaoTarefa;
	}

	public void setDescricaoTarefa(String descricaoTarefa) {
		this.descricaoTarefa = descricaoTarefa;
	}

	public Boolean getCompletada() {
		return completada;
	}

	public void setCompletada(Boolean completada) {
		this.completada = completada;
	}
	
}
