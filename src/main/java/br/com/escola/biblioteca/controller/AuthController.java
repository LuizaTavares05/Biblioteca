package br.com.escola.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import br.com.escola.biblioteca.dto.LoginDTO;
import br.com.escola.biblioteca.dto.UsuarioDTO;
import br.com.escola.biblioteca.dto.UsuarioResponseDTO;
import br.com.escola.biblioteca.entity.Usuario;
import br.com.escola.biblioteca.security.JwtService;
import br.com.escola.biblioteca.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints responsáveis pela autenticação e gerenciamento de usuários")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")

    @Operation(summary = "Realizar login", description = "Autentica um usuário e retorna um token JWT")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos")
    })

    public ResponseEntity<String> login(
            @RequestBody LoginDTO login) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()));

        UserDetails user = (UserDetails) auth.getPrincipal();

        String token = jwtService.gerarToken(
                user.getUsername());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/cadastrar")

    @Operation(summary = "Cadastrar usuário", description = "Realiza o cadastro de um novo usuário")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar usuário"),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado")
    })

    public ResponseEntity<UsuarioResponseDTO> registrar(
            @RequestBody @Valid UsuarioDTO dto) {

        Usuario usuario = usuarioService.cadastrar(dto);

        UsuarioResponseDTO response = UsuarioResponseDTO.fromEntity(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/alterarPorId/{id}")

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário pelo ID", security = @SecurityRequirement(name = "bearerAuth"))

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar usuário"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "401", description = "Token JWT inválido")
    })

    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioDTO dto) {

        Usuario usuarioAtualizado = usuarioService.atualizar(id, dto);

        UsuarioResponseDTO response = UsuarioResponseDTO.fromEntity(usuarioAtualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletarPorId/{id}")

    @Operation(summary = "Deletar usuário", description = "Remove um usuário pelo ID", security = @SecurityRequirement(name = "bearerAuth"))

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "401", description = "Token JWT inválido")
    })

    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        usuarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
