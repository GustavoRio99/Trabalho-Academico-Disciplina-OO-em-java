package br.com.sgv.controller;

import br.com.sgv.model.Supervisor;
import br.com.sgv.repository.SupervisorRepository;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupervisorController {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @GetMapping("/pessoas-juridicas")
    public String listar(Model model) {
        model.addAttribute("listaPessoasJuridicas", supervisorRepository.findAll());
        return "gerenciar_pessoas_juridicas";
    }

    @GetMapping("/pessoas-juridicas/novo")
    public String novo(Model model) {
        model.addAttribute("pessoaJuridica", new Supervisor());
        return "editar_pessoa_juridica";
    }

    @GetMapping("/pessoas-juridicas/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Supervisor> supervisor = supervisorRepository.findById(id);
        model.addAttribute("pessoaJuridica", supervisor.orElse(null));
        return "editar_pessoa_juridica";
    }

    @PostMapping("/pessoas-juridicas")
    public String salvar(@Valid Supervisor supervisor, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_pessoa_juridica";
        }
        supervisorRepository.save(supervisor);
        return "redirect:/pessoas-juridicas";
    }

    @DeleteMapping("/pessoas-juridicas/{id}")
    public String excluir(@PathVariable("id") long id) {
        supervisorRepository.deleteById(id);
        return "redirect:/pessoas-juridicas";
    }
}
