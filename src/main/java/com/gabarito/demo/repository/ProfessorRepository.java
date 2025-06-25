package com.gabarito.demo.repository;

import com.gabarito.demo.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {}
