package com.gabarito.correcao.controller;

import com.gabarito.correcao.model.Resposta;
import com.gabarito.correcao.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @PostMapping("/importar")
    public Resposta importarResposta(@RequestBody Resposta resposta) {
        return respostaService.corrigirProva(resposta);
    }
}