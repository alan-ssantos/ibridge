package br.com.ibridge.controller;

import br.com.ibridge.model.Usuario;
import br.com.ibridge.model.UsuarioBean;
import br.com.ibridge.repository.UsuarioRepository;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public String index(Usuario usuario, Model model){
        return "usuario/form";
    }

    @GetMapping("{id}")
    public String detalhes(@PathVariable int id, Model model){
        model.addAttribute("usuario", usuarioRepository.findById(id).get());
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
    public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) return "form";

        attributes.addFlashAttribute("msg", usuario.getId()==0?"Cadastrado!":"Atualizado!");
        usuarioRepository.save(usuario);
        return "redirect:form";
    }

    @GetMapping("login")
    public String login(UsuarioBean bean, Model model){
        model.addAttribute("bean", bean);
        return "usuario/login";
    }

    @PostMapping("login")
    public String logar(UsuarioBean bean, HttpSession session, RedirectAttributes attributes){
        Usuario usuario;
        usuario = usuarioRepository.findByEmailAndSenha(bean.getEmail(), bean.getSenha());

        if (usuario == null){
            attributes.addFlashAttribute("msg", "Usuário ou senha inválidos");
        }

        session.setAttribute("usuarioLogado", bean);
        attributes.addFlashAttribute("msg", session.getAttribute("usuarioLogado"));

        return "redirect:login";
    }

}
