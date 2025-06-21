package br.com.unialfa.gabarito.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario {
    private String matricula;
}