package com.br.paulochagasdev.apiagenda.api.controller;

import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;
import com.br.paulochagasdev.apiagenda.domain.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente dados){
        Paciente paciente = service.salvar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Paciente>> listar(){
        List<Paciente> pacientes = service.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

}
