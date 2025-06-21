package br.com.unialfa.gabarito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Administrador extends Usuario {

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String cargo;
}