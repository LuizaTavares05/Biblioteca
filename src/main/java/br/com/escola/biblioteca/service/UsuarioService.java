package br.com.escola.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.UsuarioDTO;
import br.com.escola.biblioteca.entity.Usuario;
import br.com.escola.biblioteca.exception.BusinessException;
import br.com.escola.biblioteca.exception.NotFoundException;
import br.com.escola.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public UserDetails loadUserByUsername(String email)
                        throws UsernameNotFoundException {

                return usuarioRepository.findByEmail(email)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "Usuário não encontrado: " + email));
        }

        public Usuario cadastrar(UsuarioDTO dto) {

                if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
                        throw new BusinessException(
                                        "E-mail já cadastrado!");
                }

                Usuario usuario = new Usuario();

                usuario.setNome(dto.getNome());
                usuario.setEmail(dto.getEmail());

                usuario.setSenha(
                                passwordEncoder.encode(dto.getSenha()));

                return usuarioRepository.save(usuario);
        }

        public Usuario atualizar(Long id, UsuarioDTO dto) {

                Usuario usuario = usuarioRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException(
                                                "Usuário não encontrado com id: " + id));

                usuarioRepository.findByEmail(dto.getEmail())
                                .ifPresent(usuarioExistente -> {

                                        if (!usuarioExistente.getId().equals(id)) {
                                                throw new BusinessException(
                                                                "E-mail já cadastrado!");
                                        }
                                });

                usuario.setNome(dto.getNome());
                usuario.setEmail(dto.getEmail());

                usuario.setSenha(
                                passwordEncoder.encode(dto.getSenha()));

                return usuarioRepository.save(usuario);
        }

        public void deletar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Usuário não encontrado com id: " + id
                        ));

        usuarioRepository.delete(usuario);
        }
}
