package com.gabarito.correcao.service;

import com.gabarito.correcao.model.Prova;
import com.gabarito.correcao.repository.ProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvaService {

    @Autowired
    private ProvaRepository provaRepository;

    public Prova salvar(Prova prova) {
        return provaRepository.save(prova);
    }

    public List<Prova> listarTodas() {
        return provaRepository.findAll();
    }

    public Prova buscarPorId(Long id) {
        return provaRepository.findById(id).orElse(null);
    }
}