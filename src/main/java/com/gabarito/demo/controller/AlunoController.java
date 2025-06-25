package com.gabarito.demo.controller;

import com.gabarito.demo.model.Aluno;
import com.gabarito.demo.model.Turma;
import com.gabarito.demo.repository.AlunoRepository;
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
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired private TurmaRepository turmaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", alunoRepository.findAll());
        return "alunos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("turmas", turmaRepository.findAll());
        return "alunos/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Aluno aluno) {
        if (aluno.getTurma() != null && aluno.getTurma().getId() != null) {
            Turma turma = turmaRepository.findById(aluno.getTurma().getId())
                    .orElse(null);
            aluno.setTurma(turma);
        }
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("aluno", alunoRepository.findById(id).orElseThrow());
        model.addAttribute("turmas", turmaRepository.findAll());
        return "alunos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        alunoRepository.deleteById(id);
        return "redirect:/alunos";
    }
}

