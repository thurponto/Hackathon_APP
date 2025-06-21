package br.com.unialfa.gabarito.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String codigo;
}