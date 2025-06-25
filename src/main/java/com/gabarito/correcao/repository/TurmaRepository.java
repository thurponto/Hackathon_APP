package com.gabarito.correcao.repository;

import com.gabarito.correcao.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    Turma findByNome(String nome);
}
