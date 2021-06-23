package com.example.demo.security.jwt;

import com.example.demo.security.services.UsuarioDetails;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    HttpServletResponse response;

    static final long EXPIRATION_TIME = 860_000_000;
    static final String SECRET = "MySecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public String gerarToken(Authentication authentication){
        UsuarioDetails usuarioProncipal = (UsuarioDetails) authentication.getPrincipal();

        String JWT = Jwts.builder()
                .setSubject((usuarioProncipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);

        return JWT;
    }

    public String getNomeUsuarioFromJwtToken(String token) {

        String user = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return user;
    }

    public boolean verificarSeOTokenEstaValido(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e){
            System.out.println(e);
        }

        return false;
    }
}
