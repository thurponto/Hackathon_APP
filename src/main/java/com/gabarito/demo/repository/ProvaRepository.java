package com.gabarito.demo.repository;

import com.gabarito.demo.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvaRepository extends JpaRepository<Prova, Long> {
    List<Prova> findByTurmaId(Long turmaId);
}

