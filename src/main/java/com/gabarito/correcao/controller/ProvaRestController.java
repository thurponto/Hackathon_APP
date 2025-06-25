package com.gabarito.correcao.controller;

import com.gabarito.correcao.model.Prova;
import com.gabarito.correcao.repository.ProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provas")
public class ProvaRestController {

    @Autowired
    private ProvaRepository provaRepository;

    @PostMapping
    public Prova criarProva(@RequestBody Prova prova) {
        return provaRepository.save(prova);
    }

    @GetMapping
    public List<Prova> listarProvas() {
        return provaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Prova buscarPorId(@PathVariable Long id) {
        return provaRepository.findById(id).orElse(null);
    }
}
