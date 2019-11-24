package br.com.ibridge.controller;

import br.com.ibridge.model.Startup;
import br.com.ibridge.model.UsuarioBean;
import br.com.ibridge.repository.StartupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("startup")
@Scope("session")
public class StartupController {

    @Autowired
    private StartupRepository startupRepository;

    @GetMapping
    public String index(){
        return "startup/lista";
    }

    @GetMapping("{id}")
    public String buscar(@PathVariable("id") int id, Model model){
        model.addAttribute("startup", startupRepository.findById(id).get());
        return "startup/detalhes";
    }

    @GetMapping("buscar")
    public String buscarPorNome(String q, Model model){
        model.addAttribute("startups", startupRepository.findByNomeContainsIgnoreCase(q));
        return "startup/lista";
    }

    @GetMapping("lista")
    public String listar(Model model, HttpSession session, RedirectAttributes attributes){
        UsuarioBean usuarioLogado = (UsuarioBean) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) model.addAttribute("usuario", usuarioLogado);

        model.addAttribute("startups", startupRepository.findAll());
        return "startup/lista";
    }

    @GetMapping("cadastrar")
    public String cadastrar(Startup startup, Model model, HttpSession session, RedirectAttributes attributes){
        UsuarioBean usuarioLogado = (UsuarioBean) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null){
            model.addAttribute("usuario", usuarioLogado);
            return "startup/form";
        }

        attributes.addFlashAttribute("msg", "É necessário estar logado!");
        return "redirect:/usuario/login";
    }

    @PostMapping("salvar")
    public String salvar(@Valid @ModelAttribute("startup") Startup startup, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()){
            return "startup/form";
        }

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
