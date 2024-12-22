package com.br.paulochagasdev.apiagenda.api.mapper;

import com.br.paulochagasdev.apiagenda.api.request.PacienteRequest;
import com.br.paulochagasdev.apiagenda.api.response.PacienteResponse;
import com.br.paulochagasdev.apiagenda.config.ModalMapperConfig;
import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest request) {
        return mapper.map(request, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente) {
        return mapper.map(paciente, PacienteResponse.class);
    }

    public List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(this::toPacienteResponse)
                .collect(Collectors.toList());
    }
}