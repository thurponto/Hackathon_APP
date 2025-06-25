package com.gabarito.correcao.service;


import com.gabarito.correcao.model.Prova;
import com.gabarito.correcao.model.Resposta;
import com.gabarito.correcao.repository.ProvaRepository;
import com.gabarito.correcao.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespostaService {
    @Autowired
    private ProvaRepository provaRepo;

    @Autowired
    private RespostaRepository respostaRepo;

    public Resposta corrigirProva(Resposta resposta) {
        Prova prova = provaRepo.findById(resposta.getProva().getId()).orElseThrow();

        String[] gabarito = prova.getGabarito().split(",");
        String[] respostas = resposta.getRespostas().split(",");

        int acertos = 0;
        for (int i = 0; i < gabarito.length; i++) {
            if (i < respostas.length && gabarito[i].equalsIgnoreCase(respostas[i])) {
                acertos++;
            }
        }

        double nota = (10.0 / gabarito.length) * acertos;
        resposta.setNota(nota);

        return respostaRepo.save(resposta);
    }
}