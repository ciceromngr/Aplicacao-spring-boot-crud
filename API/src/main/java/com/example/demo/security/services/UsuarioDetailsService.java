package com.example.demo.security.services;

import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        return UsuarioDetails.build(usuario);
    }

    public UserDetails getNomeUsuario(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(username);
        return UsuarioDetails.build(usuario);
    }
}
