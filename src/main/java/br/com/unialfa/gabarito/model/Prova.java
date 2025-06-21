package br.com.unialfa.gabarito.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Prova {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private int quantidadeQuestoes;

    @Column(nullable = false)
    private String gabaritoOficial; // Corrigi o nome do campo para seguir convenção Java

    @OneToMany(mappedBy = "prova", cascade = CascadeType.ALL)
    private List<RespostaAluno> respostasAlunos;

    // Métodos getters e setters adicionais (embora @Data do Lombok já crie)
    public String getGabaritoOficial() {
        return gabaritoOficial;
    }

    public void setGabaritoOficial(String gabaritoOficial) {
        this.gabaritoOficial = gabaritoOficial;
    }
}