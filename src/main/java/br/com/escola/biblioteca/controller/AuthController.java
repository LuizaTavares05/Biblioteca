package br.com.escola.biblioteca.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.dto.LoginDTO;
import br.com.escola.biblioteca.dto.UsuarioDTO;
import br.com.escola.biblioteca.entity.Usuario;
import br.com.escola.biblioteca.security.JwtService;
import br.com.escola.biblioteca.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login) {

     
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
        );

       
        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.gerarToken(user.getUsername());

        System.out.println(token); 
        
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        usuarioService.cadastrar(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
