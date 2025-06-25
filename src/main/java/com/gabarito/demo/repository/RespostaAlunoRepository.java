package com.gabarito.demo.repository;

import com.gabarito.demo.model.Aluno;
import com.gabarito.demo.model.RespostaAluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaAlunoRepository extends JpaRepository<RespostaAluno, Long> {
  //  RespostaAluno findByAlunoAndQuestao(Aluno aluno, Questao questao);

}

