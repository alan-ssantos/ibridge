package br.com.ibridge.controller;

import br.com.ibridge.repository.StartupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    StartupRepository startupRepository;

    @GetMapping("/index")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/contato")
    public String contato(Model model){
        return "contato";
    }

    @GetMapping("/sobre-nos")
    public String sobre(Model model){
        return "sobre-nos";
    }
}
