package br.com.unialfa.gabarito.repository;

import br.com.unialfa.gabarito.model.Aluno;
import br.com.unialfa.gabarito.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaAlunoRepository extends JpaRepository<Turma, Long> {

    @Query("SELECT t FROM Turma t JOIN t.alunos a WHERE a.id = :alunoId")
    List<Turma> findTurmasByAlunoId(Long alunoId);

    @Query("SELECT a FROM Aluno a JOIN a.turmas t WHERE t.id = :turmaId")
    List<Aluno> findAlunosByTurmaId(Long turmaId);
}