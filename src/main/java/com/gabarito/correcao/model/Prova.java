package com.gabarito.correcao.model;

import jakarta.persistence.*;

@Entity
public class Prova {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Turma turma;

    @ManyToOne
    private Disciplina disciplina;

    private String data;
    private String gabarito;


    public Prova() {
    }


    public Prova(Turma turma, Disciplina disciplina, String data, String gabarito) {
        this.turma = turma;
        this.disciplina = disciplina;
        this.data = data;
        this.gabarito = gabarito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGabarito() {
        return gabarito;
    }

    public void setGabarito(String gabarito) {
        this.gabarito = gabarito;
    }
}
