package com.br.paulochagasdev.apiagenda.api.controller;

import com.br.paulochagasdev.apiagenda.api.mapper.AgendaMapper;
import com.br.paulochagasdev.apiagenda.api.request.AgendaRequest;
import com.br.paulochagasdev.apiagenda.api.response.AgendaResponse;
import com.br.paulochagasdev.apiagenda.domain.entity.Agenda;
import com.br.paulochagasdev.apiagenda.domain.service.AgendaService;
import com.br.paulochagasdev.apiagenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService service;
    private final AgendaMapper mapper;

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@RequestBody AgendaRequest request){

        Agenda agenda = mapper.toAgenda(request);
        Agenda agendaSalva = service.salvar(agenda);
        AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);
        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AgendaResponse>> listar() {
        List<Agenda> agendas = service.listarTodos();
        List<AgendaResponse> responseList = mapper.toAgendaResponseList(agendas);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscar(@PathVariable Long id){

        Optional<Agenda> opt_agenda = service.buscar(id);

        if (opt_agenda.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        AgendaResponse agendaResponse = mapper.toAgendaResponse(opt_agenda.get());
        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);

    }
}