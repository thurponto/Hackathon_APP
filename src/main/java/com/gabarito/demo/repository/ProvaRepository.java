package com.gabarito.demo.repository;

import com.gabarito.demo.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvaRepository extends JpaRepository<Prova, Long> {}

