package com.gabarito.correcao.controller;

import com.gabarito.correcao.model.Resposta;
import com.gabarito.correcao.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private RespostaService respostaService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/painel")
    public String painelDoProfessor() {
        return "dashboard-professor";
    }

    @GetMapping("/provas/nova")
    public String novaProvaForm() {
        return "nova-prova";
    }

}
