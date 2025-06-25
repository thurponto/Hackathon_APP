package com.gabarito.demo.controller;

import com.gabarito.demo.model.Aluno;
import com.gabarito.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alunos")
public class AlunoApiController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/por-ra")
    public ResponseEntity<Aluno> buscarPorRa(@RequestParam String ra) {
        return alunoRepository.findByRa(ra)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}