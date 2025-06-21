package br.com.unialfa.gabarito.repository;

import br.com.unialfa.gabarito.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByDisciplinaId(Long disciplinaId);
    List<Turma> findByProfessorId(Long professorId);
    List<Turma> findByNomeContainingIgnoreCase(String nome);
}