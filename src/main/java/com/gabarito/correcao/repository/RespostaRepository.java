package com.gabarito.correcao.repository;

import com.gabarito.correcao.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    List<Resposta> findByProvaId(Long provaId);

    @Query("SELECT AVG(r.nota) FROM Resposta r WHERE r.prova.id = :provaId")
    Double mediaPorProva(@Param("provaId") Long provaId);

    @Query("SELECT COUNT(r) FROM Resposta r WHERE r.prova.id = :provaId")
    Long totalPorProva(@Param("provaId") Long provaId);
}