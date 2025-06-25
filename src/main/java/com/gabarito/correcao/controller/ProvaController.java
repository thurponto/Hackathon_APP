package com.gabarito.correcao.controller;

import com.gabarito.correcao.model.Disciplina;
import com.gabarito.correcao.model.Prova;
import com.gabarito.correcao.model.Turma;
import com.gabarito.correcao.repository.DisciplinaRepository;
import com.gabarito.correcao.repository.ProvaRepository;
import com.gabarito.correcao.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProvaController {

    @Autowired
    private ProvaRepository provaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping("/professor/provas/nova")
    public String novaProvaForm() {
        return "nova-prova";
    }

    @PostMapping("/provas/criar")
    public String criarProva(@RequestParam String disciplina,
                             @RequestParam String turma,
                             @RequestParam String data,
                             @RequestParam String gabarito) {


        Disciplina disciplinaObj = disciplinaRepository.findByNome(disciplina);
        Turma turmaObj = turmaRepository.findByNome(turma);

        if (disciplinaObj == null || turmaObj == null) {
            // Se não encontrar, redireciona ou mostra erro (aqui só um exemplo)
            return "redirect:/erro";
        }

        Prova prova = new Prova();
        prova.setDisciplina(disciplinaObj);
        prova.setTurma(turmaObj);
        prova.setData(data);
        prova.setGabarito(gabarito);

        provaRepository.save(prova);
        return "redirect:/dashboard-professor";
    }
}
