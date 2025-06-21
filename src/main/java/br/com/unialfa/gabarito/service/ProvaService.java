package br.com.unialfa.gabarito.service;

import br.com.unialfa.gabarito.model.RespostaAluno;
import br.com.unialfa.gabarito.repository.ProvaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProvaService {
    private final ProvaRepository provaRepository;

    public ProvaService(ProvaRepository provaRepository) {
        this.provaRepository = provaRepository;
    }

    public RespostaAluno corrigirResposta(RespostaAluno respostaAluno) {
        respostaAluno.calcularNota();
        return respostaAluno;
    }
}