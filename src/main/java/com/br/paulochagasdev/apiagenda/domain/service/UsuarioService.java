package com.br.paulochagasdev.apiagenda.domain.service;

import com.br.paulochagasdev.apiagenda.domain.entity.Usuario;
import com.br.paulochagasdev.apiagenda.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {


    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
        return new User(usuario.getUsuario(), usuario.getSenha(), new ArrayList<>());
    }

    public List<Usuario> getAll() {
        return repository.findAll();
    }

    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }
}
