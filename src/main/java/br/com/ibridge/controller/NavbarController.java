package br.com.ibridge.controller;

import br.com.ibridge.model.UsuarioBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping()
@Scope("session")
public class NavbarController {

    @GetMapping("/template")
    public String nav(HttpSession session, Model model){
        UsuarioBean usuarioLogado = (UsuarioBean) session.getAttribute("usuarioLogado");
        if (usuarioLogado != null) model.addAttribute("usuario", usuarioLogado);

        return "template";
    }

}
