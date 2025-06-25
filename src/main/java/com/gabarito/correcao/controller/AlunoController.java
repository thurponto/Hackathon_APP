package com.gabarito.correcao.controller;

import com.gabarito.correcao.model.Aluno;
import com.gabarito.correcao.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("")
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoRepository.findAll());
        return "alunos"; // alunos.html (listagem)
    }

    @GetMapping("/novo")
    public String novoAlunoForm() {
        return "novo-aluno"; // novo-aluno.html (formulário)
    }

    @PostMapping("/criar")
    public String criarAluno(@RequestParam String nome, @RequestParam String matricula) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }
}
