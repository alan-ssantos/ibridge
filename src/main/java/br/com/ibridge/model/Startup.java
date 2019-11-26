package br.com.ibridge.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "startup", sequenceName = "SQ_T_STARTUP", allocationSize = 1)
public class Startup {

    @Id
    @Column(name = "STARTUP_ID")
    @GeneratedValue(generator = "startup", strategy = GenerationType.SEQUENCE)
    private int id;

    @Size(max = 50, message = "O nome deve ter no máximo 50 letras")
    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "STARTUP_NOME", length = 50, nullable = false)
    private String nome;

    @Size(max = 200, message = "A descrição deve ter no máximo 200 letras")
    @NotBlank(message = "É necessário ter uma descrição")
    @Column(name = "STARTUP_DESCRICAO", length = 200)
    private String descricao;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;

    @Email(message = "Insira um e-mail válido")
    @NotBlank(message = "Um e-mail é obrigatório")
    @Column(name = "STARTUP_EMAIL", nullable = false)
    private String email;

    @Column(name = "STARTUP_TELEFONE")
    private String telefone;

    @Column(name = "STARTUP_SITE")
    private String site;

    @ManyToOne(cascade = CascadeType.ALL)
    @Valid
    private Endereco endereco;

    @NotBlank
    private String imagem;

    @NotNull
    @Column(name = "USUARIO_ID")
    private int usuarioId;

    //GETTERS AND SETTERS

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
