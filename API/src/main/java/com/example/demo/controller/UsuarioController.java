package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UsuarioDto;
import com.example.demo.models.Usuario;
import com.example.demo.service.UsuarioService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/allUser")
	public List<Usuario> pegarTodosUsuarios(HttpServletRequest request) throws Exception{
		return usuarioService.pegarTodosUsuario(request);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) throws Exception {
		return usuarioService.cadastrarUsuario(usuario);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluirUsuario(@PathVariable(value = "id") Long id, HttpServletRequest request) throws Exception {
		usuarioService.deletarUsuario(id, request);
	}
}
