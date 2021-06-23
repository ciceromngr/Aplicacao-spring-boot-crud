package com.example.demo.models;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Size;

import javax.persistence.*;

@Entity
@Table(name = "usuario_tb")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 30)
	private String nome;
	@Size(max = 50)
	private String email;
	@Size(min = 6, max = 18)
	private String senha;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idUsuario")
	@Nullable
	private List<Tarefas> tarefa;

	@Nullable
	private String role;
	
	public Usuario() {
		super();
	}

	public Usuario(Long id, String nome, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String name) {
		this.nome = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Nullable public String getRole() {return role;}
	public void setRole(@Nullable String role) {this.role = role;}

	@Nullable
	public List<Tarefas> getTarefa() {
		return tarefa;
	}

	public void setTarefa(@Nullable List<Tarefas> tarefa) {
		this.tarefa = tarefa;
	}
}
