package com.gabarito.correcao.repository;

import com.gabarito.correcao.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    Disciplina findByNome(String nome); // opcional, usado no ProvaController
}
