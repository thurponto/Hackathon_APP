package com.gabarito.demo.service;

import com.gabarito.demo.model.Aluno;
import com.gabarito.demo.model.Prova;
import com.gabarito.demo.model.RespostaAluno;
import com.gabarito.demo.repository.RespostaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvaService {

    @Autowired
    private RespostaAlunoRepository respostaAlunoRepository;




}