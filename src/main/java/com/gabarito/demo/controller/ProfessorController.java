package com.gabarito.demo.controller;

import com.gabarito.demo.model.Professor;
import com.gabarito.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/professores")
public class    ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("professores", professorRepository.findAll());
        return "professores/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("professor", new Professor());
        return "professores/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Professor professor) {
        professorRepository.save(professor);
        return "redirect:/professores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("professor", professorRepository.findById(id).orElseThrow());
        return "professores/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        professorRepository.deleteById(id);
        return "redirect:/professores";
    }
}