package com.br.paulochagasdev.apiagenda.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agenda")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_hora")
    private LocalDateTime horario;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    private Paciente paciente;

}
