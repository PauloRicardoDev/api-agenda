package com.br.paulochagasdev.apiagenda.api.request;

import com.br.paulochagasdev.apiagenda.domain.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {

    @NotBlank
    private String descricao;
    @NotNull
    @Future
    private LocalDateTime horario;
    @NotNull
    private Long pacienteId;

}
