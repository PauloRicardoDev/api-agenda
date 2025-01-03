package com.br.paulochagasdev.apiagenda.api.mapper;

import com.br.paulochagasdev.apiagenda.api.request.AgendaRequest;
import com.br.paulochagasdev.apiagenda.api.response.AgendaResponse;
import com.br.paulochagasdev.apiagenda.domain.entity.Agenda;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    private final ModelMapper mapper;

    public Agenda toAgenda (AgendaRequest request){
        return mapper.map(request, Agenda.class);
    }

    public AgendaResponse toAgendaResponse(Agenda agenda) {
        return mapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> toAgendaResponseList(List<Agenda> agenda) {
        return agenda.stream()
                .map(this::toAgendaResponse)
                .collect(Collectors.toList());
    }
}
