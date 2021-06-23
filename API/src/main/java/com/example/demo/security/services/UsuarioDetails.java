package com.example.demo.security.services;

import com.example.demo.models.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioDetails implements UserDetails {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioDetails(Long id, String nome, String email, String senha, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.authorities = authorities;
    }

    public static UsuarioDetails build(Usuario usuario){
        Set<String> role = new HashSet<>();
        role.add(usuario.getRole());

        List<GrantedAuthority> authorities = role.stream().map(r ->
                new SimpleGrantedAuthority(r)).collect(Collectors.toList());

        return new UsuarioDetails(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
