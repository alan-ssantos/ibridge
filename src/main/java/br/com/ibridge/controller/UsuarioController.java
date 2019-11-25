package br.com.ibridge.controller;

import br.com.ibridge.model.Usuario;
import br.com.ibridge.model.UsuarioBean;
import br.com.ibridge.repository.StartupRepository;
import br.com.ibridge.repository.UsuarioRepository;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("usuario")
@Scope("session")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private StartupRepository startupRepository;

    @GetMapping
    public String index(Usuario usuario, Model model){
        return "usuario/form";
    }

    @GetMapping("{id}")
    public String detalhes(@PathVariable int id, Model model){
        model.addAttribute("usuario", usuarioRepository.findById(id).get());
        model.addAttribute("startups", startupRepository.findAllByUsuarioId(id));
        return "usuario/detalhes";
    }

    @GetMapping("cadastrar")
    public String cadastrar(Usuario usuario, Model model){
        return "usuario/form";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable int id, Model model){
        model.addAttribute("usuario", usuarioRepository.findById(id).get());
        return "usuario/form";
    }

    @PostMapping("salvar")
    public String salvar(@Valid     Usuario usuario, BindingResult result, RedirectAttributes attributes, Model model, HttpSession session){
        if (result.hasErrors()) return "usuario/form";

        attributes.addFlashAttribute("msg", usuario.getId()==0?"Cadastrado!":"Atualizado!");
        usuarioRepository.save(usuario);

        session.setAttribute("logado", usuario);
        model.addAttribute("usuario", usuario);
        return "redirect:/usuario/" + usuario.getId();
    }

    @GetMapping("login")
    public String login(UsuarioBean bean, Model model){
        model.addAttribute("bean", bean);
        return "usuario/login";
    }

    @PostMapping("login")
    public String logar(UsuarioBean bean, HttpSession session, RedirectAttributes attributes){
        Usuario usuario = usuarioRepository.findByEmailAndSenha(bean.getEmail(), bean.getSenha());

        if (usuario != null){
            session.setAttribute("logado", usuario);
            attributes.addFlashAttribute("msg", "Bem vindo!");
            return "redirect:/usuario/" + usuario.getId();
        }

        attributes.addFlashAttribute("msg", "Usuário ou senha inválidos");
        return "redirect:login";
    }

    @GetMapping("logout")
    public String sair (HttpSession session){
        session.removeAttribute("logado");
        return "redirect:/";
    }

}
