package br.com.escola.biblioteca.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.escola.biblioteca.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
    	
    	String path = request.getServletPath();

    	if (path.equals("/auth/login") || path.equals("/auth/registrar")) {
    	    chain.doFilter(request, response);
    	    return;
    	}
    	

        // 1. Pega o cabeçalho Authorization da requisição
        String header = request.getHeader("Authorization");

        // 2. Verifica se veio um token no formato "Bearer <token>"
        if (header != null && header.startsWith("Bearer ")) {

            // 3. Remove a palavra "Bearer " e fica só com o token
            String token = header.substring(7);

            // 4. Valida o token e extrai o username (e-mail)
            String username = jwtService.validarToken(token);

            // 5. Se o token é válido e não há autenticação ativa na sessão
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 6. Busca o usuário no banco de dados
                UserDetails user = usuarioService.loadUserByUsername(username);

                // 7. Cria o objeto de autenticação e registra no contexto do Spring Security
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // 8. Deixa a requisição continuar
        chain.doFilter(request, response);
    }
}
