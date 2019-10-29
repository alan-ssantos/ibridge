package br.com.ibridge.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "startup", sequenceName = "T_SQ_STARTUP", allocationSize = 1)
public class Startup {

    @Id
    @GeneratedValue(generator = "startup", strategy = GenerationType.SEQUENCE)
    @Column(name = "STARTUP_ID")
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

    /*
    * ENDERECO (RUA, NUMERO, BAIRRO, CIDADE, ESTADO)
    * CONTATO (EMAIL E TELEFONE)
    * CATEGORIA *
    * QTD DE FUNCIONARIOS (RANGE)
    * LINK PARA SITE OFICIAL *
    * */

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

}
