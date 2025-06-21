package br.com.unialfa.gabarito.repository;

import br.com.unialfa.gabarito.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    Optional<Disciplina> findByCodigo(String codigo);
    List<Disciplina> findByNomeContainingIgnoreCase(String nome);
    boolean existsByCodigo(String codigo);
}