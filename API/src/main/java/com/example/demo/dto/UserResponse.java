package com.example.demo.dto;

import java.util.List;

public class UserResponse {

    private Long id;
    private String nome;
    private String email;
    private String token;
    private List<String> role;

    public UserResponse(){super();}

    public UserResponse(Long id, String nome, String email, String token, List<String> role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.token = token;
        this.role = role;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
