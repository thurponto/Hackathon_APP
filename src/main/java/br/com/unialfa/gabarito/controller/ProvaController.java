package br.com.unialfa.gabarito.controller;

import br.com.unialfa.gabarito.model.RespostaAluno;
import br.com.unialfa.gabarito.service.ProvaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/provas")
public class ProvaController {
    private final ProvaService provaService;

    public ProvaController(ProvaService provaService) {
        this.provaService = provaService;
    }

    @PostMapping("/corrigir")
    public RespostaAluno corrigirProva(@RequestBody RespostaAluno respostaAluno) {
        return provaService.corrigirResposta(respostaAluno);
    }
}