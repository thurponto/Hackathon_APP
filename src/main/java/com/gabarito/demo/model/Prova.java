package com.gabarito.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Prova {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private LocalDate data;

    @ManyToOne
    private Turma turma;

    @ManyToOne
    @JsonIgnore
    private Disciplina disciplina;

    @Column(columnDefinition = "TEXT")
    private String alternativasCorretas;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public Turma getTurma() {
        return turma;
    }
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getAlternativasCorretas() {
        return alternativasCorretas;
    }

    public void setAlternativasCorretas(String alternativasCorretas) {
        this.alternativasCorretas = alternativasCorretas;
    }
}
