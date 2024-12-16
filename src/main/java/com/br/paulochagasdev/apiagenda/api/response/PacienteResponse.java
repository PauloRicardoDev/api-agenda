package com.br.paulochagasdev.apiagenda.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {

    private Long id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;

}
