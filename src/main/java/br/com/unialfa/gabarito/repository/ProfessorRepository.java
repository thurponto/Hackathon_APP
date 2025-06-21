package br.com.unialfa.gabarito.repository;

import br.com.unialfa.gabarito.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByNomeContainingIgnoreCase(String nome);
}