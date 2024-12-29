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
        boolean existEmail = false;

        Optional<Paciente> opt_paciente_cpf = repository.findByCpf(paciente.getCpf());
        if (opt_paciente_cpf.isPresent()) {
            if (!opt_paciente_cpf.get().getId().equals(paciente.getId())) {
                existCpf = true;
            }
        }

        Optional<Paciente> opt_paciente_email = repository.findByEmail(paciente.getEmail());
        if (opt_paciente_email.isPresent()) {
            if (!opt_paciente_email.get().getId().equals(paciente.getId())) {
                existEmail = true;
            }
        }

        if (existCpf) {
            throw new BusinessException("CPF já cadastrado");
        }

        if (existEmail) {
            throw new BusinessException("E-mail já cadastrado");
        }

        return repository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente paciente) {

        Optional<Paciente> opt_paciente = buscar(id);

        if (opt_paciente.isEmpty()) {
            throw new BusinessException("Usuário não encontrado!");
        }

        paciente.setId(id);
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
