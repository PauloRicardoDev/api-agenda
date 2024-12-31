package com.br.paulochagasdev.apiagenda.api.controller;

import com.br.paulochagasdev.apiagenda.api.mapper.PacienteMapper;
import com.br.paulochagasdev.apiagenda.api.request.PacienteRequest;
import com.br.paulochagasdev.apiagenda.api.response.PacienteResponse;
import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;
import com.br.paulochagasdev.apiagenda.domain.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;
    private final PacienteMapper mapper;

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid  @RequestBody PacienteRequest request){

        Paciente paciente = mapper.toPaciente(request);
        Paciente pacienteSalvo = service.salvar(paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PacienteResponse> alterar(@Valid @PathVariable Long id, @RequestBody PacienteRequest request){

        Paciente paciente = mapper.toPaciente(request);
        Paciente pacienteSalvo = service.atualizar(id,paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);

        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteResponse>> listar(){
        List<Paciente> pacientes = service.listarTodos();
        List<PacienteResponse> pacienteResponse = mapper.toPacienteResponseList(pacientes);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id){
        Optional<Paciente> opt_paciente = service.buscar(id);

        if (opt_paciente.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(opt_paciente.get()));
    }

    @DeleteMapping("excluir/{id}")
    public ResponseEntity<Paciente> apagar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
