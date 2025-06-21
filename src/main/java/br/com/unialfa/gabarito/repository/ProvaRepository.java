package br.com.unialfa.gabarito.repository;

import br.com.unialfa.gabarito.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {
    List<Prova> findByTurmaId(Long turmaId);
    List<Prova> findByDataBetween(LocalDate inicio, LocalDate fim);
    List<Prova> findByTurmaDisciplinaId(Long disciplinaId);
}