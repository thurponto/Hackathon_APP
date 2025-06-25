package com.gabarito.correcao.controller;

import com.gabarito.correcao.model.Disciplina;
import com.gabarito.correcao.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // Exibir formulário de nova disciplina
    @GetMapping("/nova")
    public String novaDisciplinaForm() {
        return "nova-disciplina"; // nome da página HTML
    }

    // Cadastrar nova disciplina
    @PostMapping("/criar")
    public String criarDisciplina(@RequestParam String nome) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplinaRepository.save(disciplina);
        return "redirect:/disciplinas/listar";
    }

    // Listar disciplinas (opcional)
    @GetMapping("/listar")
    public String listarDisciplinas(Model model) {
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        return "lista-disciplinas"; // nome da página HTML
    }

}
