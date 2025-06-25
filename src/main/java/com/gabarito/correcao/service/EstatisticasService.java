package com.gabarito.correcao.service;

import com.gabarito.correcao.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EstatisticasService {

    @Autowired
    private RespostaRepository respostaRepository;

    public Map<String, Object> calcularMediaEQuantidade(Long provaId) {
        Double media = respostaRepository.mediaPorProva(provaId);
        Long total = respostaRepository.totalPorProva(provaId);

        Map<String, Object> dados = new HashMap<>();
        dados.put("media", media);
        dados.put("totalAlunos", total);
        return dados;
    }
}

