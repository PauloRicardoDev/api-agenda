package com.br.paulochagasdev.apiagenda.api.controller;

import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;
import com.br.paulochagasdev.apiagenda.domain.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/atualizar")
    public ResponseEntity<Paciente> alterar(@RequestBody Paciente dados){
        Paciente paciente = service.salvar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listar(){
        List<Paciente> pacientes = service.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id){
        Optional<Paciente> opt_paciente = service.buscar(id);
        if (opt_paciente.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        Paciente paciente = opt_paciente.get();

        return ResponseEntity.status(HttpStatus.OK).body(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> apagar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
