package com.gabarito.demo.controller;

import com.gabarito.demo.model.Prova;
import com.gabarito.demo.repository.AlunoRepository;
import com.gabarito.demo.repository.DisciplinaRepository;
import com.gabarito.demo.repository.ProfessorRepository;
import com.gabarito.demo.repository.ProvaRepository;
import com.gabarito.demo.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProvaRepository provaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("provasRecentes", provaRepository.findAll());
        model.addAttribute("professores", professorRepository.findAll());
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        model.addAttribute("alunos", alunoRepository.findAll());
        return "home";
    }
}