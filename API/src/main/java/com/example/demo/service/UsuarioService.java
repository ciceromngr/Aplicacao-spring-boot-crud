package com.example.demo.service;

import java.util.List;

import com.example.demo.security.services.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioDto;
import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

import javax.servlet.http.HttpServletRequest;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public List<Usuario> pegarTodosUsuario(HttpServletRequest request) throws Exception{

		Usuario usuario = usuarioRepository.findByNome(request.getUserPrincipal().getName());

		if(usuario.getRole().equals("ADMIN")){
			return usuarioRepository.findAll();
		}else {
			throw new Exception("Você não tem permição de ADMIN");
		}
	}
	
	public ResponseEntity<?> cadastrarUsuario(Usuario user) throws Exception{
		if(user.getEmail() != null && user.getNome() != null && user.getSenha() != null && user.getRole() == null){
			switch (user.getEmail()){
				case "admin@admin.com":
					user.setRole("ADMIN");
					user.setSenha(passwordEncoder.encode(user.getSenha()));
					usuarioRepository.save(user);
					break;
				case "mod@mod.com":
					user.setRole("MODERADOR");
					user.setSenha(passwordEncoder.encode(user.getSenha()));
					usuarioRepository.save(user);
					break;
				default:
					user.setRole("USUARIO");
					user.setSenha(passwordEncoder.encode(user.getSenha()));
					usuarioRepository.save(user);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body("Conta Criada com sucesso!");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coluna Role não existe !");
		}
	}
	public void deletarUsuario(Long id, HttpServletRequest req) throws Exception{
		Usuario usuario = usuarioRepository.findByNome(req.getUserPrincipal().getName());
		if(usuario.getRole().equals("ADMIN")){
			usuarioRepository.deleteById(id);
		}else {
			throw new Exception("Está funcao so é permitida para o ADMIN");
		}
	}
}
