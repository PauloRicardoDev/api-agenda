package com.br.paulochagasdev.apiagenda.domain.service;

import com.br.paulochagasdev.apiagenda.domain.entity.Agenda;
import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;
import com.br.paulochagasdev.apiagenda.domain.repository.AgendaRepository;
import com.br.paulochagasdev.apiagenda.exception.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {

    @InjectMocks
    AgendaService service;

    @Mock
    PacienteService pacienteService;

    @Mock
    AgendaRepository repository;

    @Captor
    ArgumentCaptor<Agenda> agendaCaptor;


    @Test
    @DisplayName("Deve salvar agendamento com sucesso")
    void salvarComSucesso() {

        // arrange
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descricao do agendamento");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Paciente");

        agenda.setPaciente(paciente);

        Mockito.when(pacienteService.buscar(agenda.getPaciente().getId())).thenReturn(Optional.of(paciente));
        Mockito.when(repository.findByHorario(now)).thenReturn(Optional.empty());

        // action
        service.salvar(agenda);

        // assertions
        Mockito.verify(pacienteService).buscar(ArgumentMatchers.any());
        Mockito.verify(pacienteService).buscar(agenda.getPaciente().getId());
        Mockito.verify(repository).findByHorario(now);

        Mockito.verify(repository).save(agendaCaptor.capture());
        Agenda agendaSalva = agendaCaptor.getValue();

        Assertions.assertThat(agendaSalva.getPaciente()).isNotNull();
        Assertions.assertThat(agendaSalva.getDataCriacao()).isNotNull();
    }

    @Test
    @DisplayName("Não deve salvar agendamento sem paciente")
    void salvarErroPacienteNaoEncontrado() {

        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descricao do agendamento");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Paciente");

        agenda.setPaciente(paciente);

        Mockito.when(pacienteService.buscar(ArgumentMatchers.any())).thenReturn(Optional.empty());

        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            service.salvar(agenda);
        });

        Assertions.assertThat(businessException.getMessage()).isEqualTo("Paciente não encontrado");
    }

}