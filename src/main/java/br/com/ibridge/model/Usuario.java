package br.com.ibridge.model;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "usuario", sequenceName = "SQ_T_USUARIO", allocationSize = 1)
@Scope("session")
public class Usuario {

    @Id
    @Column(name = "USUARIO_ID")
    @GeneratedValue(generator = "usuario", strategy = GenerationType.SEQUENCE)
    private int id;

    @NotBlank(message = "O Nome é obrigatório")
    @Column(name = "USUARIO_NOME", nullable = false, length = 150)
    private String nome;

    @NotBlank(message = "O E-mail é obrigatório")
    @Column(name = "USUARIO_EMAIL", nullable = false, unique = true)
    private String email;

    @Min(value = 6, message = "A senha deve ter no minimo 6 caracteres")
    @Column(name = "USUARIO_SENHA", nullable = false)
    private String senha;

    @Column(name = "USUARIO_TELEFONE")
    private String telefone;

    @Past
    @Column(name = "USUARIO_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "USUARIO_INVESTIDOR")
    private boolean investidor;

    @ManyToOne(cascade = CascadeType.ALL)
    @Valid
    private Endereco endereco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isInvestidor() {
        return investidor;
    }

    public void setInvestidor(boolean investidor) {
        this.investidor = investidor;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
