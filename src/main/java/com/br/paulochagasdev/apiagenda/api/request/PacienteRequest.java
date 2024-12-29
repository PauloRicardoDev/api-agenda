package com.br.paulochagasdev.apiagenda.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

    @NotBlank(message = "O campo (nome) do paciente é obrigatório")
    private String nome;
    @NotBlank(message = "O campo (sobrenome) do paciente é obrigatório")
    private String sobrenome;
    @NotBlank(message = "O campo (cpf) do paciente é obrigatório")
    private String cpf;
    private String email;

}
