package br.com.unialfa.gabarito.repository;

import br.com.unialfa.gabarito.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByMatricula(String matricula);
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
    boolean existsByMatricula(String matricula);
}