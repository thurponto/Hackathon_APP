package com.gabarito.demo.controller;

import com.gabarito.demo.model.Disciplina;
import com.gabarito.demo.model.Professor;
import com.gabarito.demo.repository.DisciplinaRepository;
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
@RequestMapping("/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        return "disciplinas/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("professores", professorRepository.findAll());
        return "disciplinas/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Disciplina disciplina) {
        if (disciplina.getProfessor() != null && disciplina.getProfessor().getId() != null) {
            Professor professor = professorRepository.findById(disciplina.getProfessor().getId())
                    .orElse(null);
            disciplina.setProfessor(professor);
        }
        disciplinaRepository.save(disciplina);
        return "redirect:/disciplinas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("disciplina", disciplinaRepository.findById(id).orElseThrow());
        model.addAttribute("professores", professorRepository.findAll());
        return "disciplinas/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        disciplinaRepository.deleteById(id);
        return "redirect:/disciplinas";
    }
}