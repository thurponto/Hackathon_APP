package br.com.unialfa.gabarito.repository;

import br.com.unialfa.gabarito.model.RespostaAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespostaAlunoRepository extends JpaRepository<RespostaAluno, Long> {
    Optional<RespostaAluno> findByProvaIdAndAlunoId(Long provaId, Long alunoId);
    List<RespostaAluno> findByProvaId(Long provaId);
    List<RespostaAluno> findByAlunoId(Long alunoId);
    boolean existsByProvaIdAndAlunoId(Long provaId, Long alunoId);
}