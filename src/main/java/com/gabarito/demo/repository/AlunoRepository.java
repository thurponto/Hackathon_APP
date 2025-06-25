package com.gabarito.demo.repository;

import com.gabarito.demo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByRa(String ra);
}
