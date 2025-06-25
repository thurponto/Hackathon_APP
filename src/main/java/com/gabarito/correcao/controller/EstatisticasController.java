package com.gabarito.correcao.controller;

import com.gabarito.correcao.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    @Autowired
    private RespostaRepository respostaRepository;

    @GetMapping("/media/{provaId}")
    public Map<String, Object> mediaDaProva(@PathVariable Long provaId) {
        Double media = respostaRepository.mediaPorProva(provaId);
        Long total = respostaRepository.totalPorProva(provaId);

        Map<String, Object> dados = new HashMap<>();
        dados.put("media", media);
        dados.put("totalAlunos", total);
        return dados;
    }
}
