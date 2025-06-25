package com.gabarito.correcao.controller;

import com.gabarito.correcao.model.Resposta;
import com.gabarito.correcao.repository.RespostaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ResultadoController {

    private final RespostaRepository respostaRepository;

    public ResultadoController(RespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }

    @GetMapping("/provas/resultados")
    public String mostrarResultados(Model model) {
        List<Resposta> respostas = respostaRepository.findAll();
        model.addAttribute("respostas", respostas);
        return "resultados";
    }
}