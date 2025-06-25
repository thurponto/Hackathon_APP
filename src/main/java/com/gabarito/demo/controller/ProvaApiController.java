package com.gabarito.demo.controller;

import com.gabarito.demo.model.Aluno;
import com.gabarito.demo.model.Prova;
import com.gabarito.demo.repository.AlunoRepository;
import com.gabarito.demo.repository.ProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/provas")
public class ProvaApiController {

    @Autowired
    private ProvaRepository provaRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/corrigir")
    public Map<String, Object> corrigirProva(
            @RequestParam Long idAluno,
            @RequestParam Long idProva,
            @RequestParam String respostasAluno) {

        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado"));
        Prova prova = provaRepository.findById(idProva)
                .orElseThrow(() -> new NoSuchElementException("Prova não encontrada"));

        // Corrija o split para usar vírgula, se o banco tem alternativas separadas por vírgula
        String[] respostasCorretas = prova.getAlternativasCorretas().split(",");

        String[] respostas = respostasAluno.split(";");

        int acertos = 0;
        int total = Math.min(respostasCorretas.length, respostas.length);

        for (int i = 0; i < total; i++) {
            if (respostas[i] != null && !respostas[i].trim().isEmpty()) {
                if (respostasCorretas[i].trim().equalsIgnoreCase(respostas[i].trim())) {
                    acertos++;
                }
            }
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("aluno", aluno.getNome());
        resultado.put("prova", prova.getTitulo());
        resultado.put("totalQuestoes", respostasCorretas.length);
        resultado.put("respondidas", respostas.length);
        resultado.put("acertos", acertos);
        resultado.put("erros", respostasCorretas.length - acertos);

        return resultado;
    }


    @GetMapping("/disponiveis")
    public List<Prova> listarProvasDisponiveis(@RequestParam Long idAluno) {
        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado"));

        if (aluno.getTurma() == null) {
            throw new IllegalStateException("Aluno não está associado a nenhuma turma.");
        }

        return provaRepository.findByTurmaId(aluno.getTurma().getId());
    }
}