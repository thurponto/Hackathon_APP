package br.com.unialfa.gabarito.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Aluno extends Usuario {
    private String matricula;

    @ManyToMany(mappedBy = "alunos")
    private List<Turma> turmas;
}