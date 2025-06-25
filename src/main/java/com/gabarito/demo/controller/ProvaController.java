package com.gabarito.demo.controller;


import com.gabarito.demo.model.Aluno;
import com.gabarito.demo.model.Disciplina;
import com.gabarito.demo.model.Prova;
import com.gabarito.demo.model.Turma;
import com.gabarito.demo.repository.AlunoRepository;
import com.gabarito.demo.repository.DisciplinaRepository;
import com.gabarito.demo.repository.ProvaRepository;
import com.gabarito.demo.repository.TurmaRepository;
import com.gabarito.demo.service.ProvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/provas")
public class ProvaController {
    @Autowired private ProvaRepository provaRepository;
    @Autowired private AlunoRepository alunoRepository;
    @Autowired private ProvaService provaService;
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public String listarProvas(Model model) {
        List<Prova> provas = provaRepository.findAll();
        model.addAttribute("provas", provas);
        return "provas/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("prova", new Prova());
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        return "provas/form";
    }

    @GetMapping("/{id}/notas")
    public String verNotas(@PathVariable Long id, Model model) {
        Prova prova = provaRepository.findById(id).orElseThrow();
        List<Aluno> alunos = alunoRepository.findAll();
        Map<Aluno, Double> notas = new HashMap<>();

        model.addAttribute("prova", prova);
        model.addAttribute("notas", notas);
        return "provas/notas";
    }

    @PostMapping
    public String salvar(@ModelAttribute Prova prova, @RequestParam(name = "alternativasCorretas", required = false) String alternativasCorretas) {
        if (prova.getTurma() != null && prova.getTurma().getId() != null) {
            Turma turma = turmaRepository.findById(prova.getTurma().getId()).orElse(null);
            prova.setTurma(turma);
        }
        if (prova.getDisciplina() != null && prova.getDisciplina().getId() != null) {
            Disciplina disciplina = disciplinaRepository.findById(prova.getDisciplina().getId()).orElse(null);
            prova.setDisciplina(disciplina);
        }

        if (alternativasCorretas != null) {
            prova.setAlternativasCorretas(alternativasCorretas.trim());
        }

        provaRepository.save(prova);
        return "redirect:/provas";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Prova prova = provaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Prova não encontrada"));
        model.addAttribute("prova", prova);
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        return "provas/form";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        provaRepository.deleteById(id);
        return "redirect:/provas";
    }

}