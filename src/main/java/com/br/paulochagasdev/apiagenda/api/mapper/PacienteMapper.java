package com.br.paulochagasdev.apiagenda.api.mapper;

import com.br.paulochagasdev.apiagenda.api.request.PacienteRequest;
import com.br.paulochagasdev.apiagenda.api.response.PacienteResponse;
import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;

public class PacienteMapper {

    public static Paciente toPaciente(PacienteRequest request){

        Paciente paciente = new Paciente();
        paciente.setCpf(request.getCpf());
        paciente.setNome(request.getNome());
        paciente.setSobrenome(request.getSobrenome());
        paciente.setEmail(request.getEmail());

        return paciente;
    }

    public static PacienteResponse toPacienteResponse(Paciente paciente){

        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        response.setCpf(paciente.getCpf());
        response.setNome(paciente.getNome());
        response.setSobrenome(paciente.getSobrenome());
        response.setEmail(paciente.getEmail());

        return response;
    }
}
