package com.br.paulochagasdev.apiagenda.domain.service;

import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;
import com.br.paulochagasdev.apiagenda.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente){
        return repository.save(paciente);
    }

    public List<Paciente> listarTodos(){
        return repository.findAll();
    }

   public Optional<Paciente> buscar(Long id){
        return repository.findById(id);
   }

   public void delete(Long id){
        repository.deleteById(id);
   }
}
