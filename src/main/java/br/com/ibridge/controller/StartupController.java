package br.com.ibridge.controller;

import br.com.ibridge.model.Startup;
import br.com.ibridge.repository.StartupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("startup")
public class StartupController {

    @Autowired
    private StartupRepository startupRepository;

    @GetMapping("{id}")
    public String buscar(@PathVariable("id") int id, Model model){
        model.addAttribute("startup", startupRepository.findById(id).get());
        return "startup/index";
    }

    @GetMapping("lista")
    public String listar(Model model){
        model.addAttribute("startups", startupRepository.findAll());
        return "startup/lista";
    }

    @GetMapping("cadastrar")
    public String cadastrar(Startup startup, Model model){
        return "startup/form";
    }

    @PostMapping("salvar")
    public String salvar(@Valid Startup startup, BindingResult result, RedirectAttributes redirectAttributes){
        result.getModel();
        if (result.hasErrors()) return "/startup/form";

        redirectAttributes.addFlashAttribute("msg", startup.getId()==0?"Startup cadastrada!":"Startup atualizada!");
        startupRepository.save(startup);
        return "redirect:/startup/lista";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") int id, Model model){
        model.addAttribute("startup", startupRepository.findById(id).get());
        return "startup/form";
    }

    @PostMapping("deletar/{id}")
    public String deletar(@PathVariable int id, RedirectAttributes redirectAttributes){
        startupRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("msg", "Startup removida com sucesso");
        return "redirect:/startup/listar";
    }
}
