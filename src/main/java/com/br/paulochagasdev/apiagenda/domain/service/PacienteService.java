package com.br.paulochagasdev.apiagenda.domain.service;

import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;
import com.br.paulochagasdev.apiagenda.domain.repository.PacienteRepository;
import com.br.paulochagasdev.apiagenda.exception.BusinessException;
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

        boolean existCpf = false;
        Optional<Paciente> opt_paciente = repository.findByCpf(paciente.getCpf());

        if (opt_paciente.isPresent()){
            if (!opt_paciente.get().getId().equals(paciente.getId())){
                existCpf = true;
            }
        }

        if (existCpf){
            throw new BusinessException("Cpf já cadastrado");
        }

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
