package com.example.demo.controller;


import com.example.demo.auth.JwtResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UsuarioDto;
import com.example.demo.security.jwt.JwtUtil;
import com.example.demo.security.services.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticationUsuario(@RequestBody UsuarioDto usuarioDto, HttpServletRequest req){

        UsernamePasswordAuthenticationToken ut = new UsernamePasswordAuthenticationToken(usuarioDto.getEmail(),usuarioDto.getSenha());
        Authentication authentication = authenticationManager.authenticate(ut);

        String jwt = jwtUtil.gerarToken(authentication);

        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();

        List<String> r = usuarioDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UserResponse(
                usuarioDetails.getId(),
                usuarioDetails.getUsername(),
                usuarioDetails.getEmail(),
                jwt,
                r
        ));
    }
}
