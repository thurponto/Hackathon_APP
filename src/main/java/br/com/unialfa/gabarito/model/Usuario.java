package br.com.unialfa.gabarito.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo; // ADMIN, PROFESSOR, ALUNO

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Outros getters e setters que você possa precisar
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}