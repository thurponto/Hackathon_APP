package br.com.unialfa.gabarito.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RespostaAluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prova_id", nullable = false)
    private Prova prova;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Column(nullable = false)
    private String respostas;

    @Column(nullable = false)
    private int acertos;

    @Column(nullable = false)
    private double nota;

    // Métodos getters e setters adicionais
    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    public String getRespostas() {
        return respostas;
    }

    public void setRespostas(String respostas) {
        this.respostas = respostas;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void calcularNota() {
        if (prova != null && prova.getGabaritoOficial() != null && respostas != null) {
            String gabarito = prova.getGabaritoOficial();
            int totalQuestoes = gabarito.length();
            this.acertos = 0;

            for (int i = 0; i < gabarito.length() && i < respostas.length(); i++) {
                if (gabarito.charAt(i) == respostas.charAt(i)) {
                    this.acertos++;
                }
            }

            this.nota = (this.acertos * 10.0) / totalQuestoes;
        }
    }
}