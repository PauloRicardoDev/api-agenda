package com.br.paulochagasdev.apiagenda.domain.service;

import com.br.paulochagasdev.apiagenda.domain.entity.Agenda;
import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;
import com.br.paulochagasdev.apiagenda.domain.repository.AgendaRepository;
import com.br.paulochagasdev.apiagenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository repository;
    private final PacienteService pacienteService;

    public Agenda salvar(Agenda agenda) {

        Optional<Paciente> opt_paciente = pacienteService.buscar(agenda.getPaciente().getId());

        if (opt_paciente.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }

        Optional<Agenda> opt_horario = repository.findByHorario(agenda.getHorario());

        if (opt_horario.isPresent()) {
            throw new BusinessException("Horário já possuí agendamento");
        }

        agenda.setPaciente(opt_paciente.get());
        agenda.setHorario(LocalDateTime.now());

        return repository.save(agenda);
    }

    public List<Agenda> listarTodos() {
        return repository.findAll();
    }

    public Optional<Agenda> buscar(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
