package br.com.unialfa.gabarito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario {

    @Column(nullable = false, unique = true)
    private String matriculaDocente;

    @Column(nullable = false)
    private String formacaoAcademica;

    @Column(nullable = false)
    private String areaEspecializacao;

    @Column(nullable = false)
    private LocalDate dataContratacao;

    @Column(nullable = false)
    private boolean coordenador = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegimeTrabalho regimeTrabalho;

    @Column(length = 20)
    private String telefoneInstitucional;

    public enum RegimeTrabalho {
        INTEGRAL,
        PARCIAL,
        DEDICACAO_EXCLUSIVA
    }
}