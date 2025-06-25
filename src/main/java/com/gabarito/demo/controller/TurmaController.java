package com.gabarito.demo.controller;

import com.gabarito.demo.model.Turma;
import com.gabarito.demo.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("turmas", turmaRepository.findAll());
        return "turmas/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("turma", new Turma());
        return "turmas/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Turma turma) {
        turmaRepository.save(turma);
        return "redirect:/turmas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("turma", turmaRepository.findById(id).orElseThrow());
        return "turmas/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        turmaRepository.deleteById(id);
        return "redirect:/turmas";
    }
}

