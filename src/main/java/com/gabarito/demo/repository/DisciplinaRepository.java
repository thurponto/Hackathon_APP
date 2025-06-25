package com.gabarito.demo.repository;

import com.gabarito.demo.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {}

