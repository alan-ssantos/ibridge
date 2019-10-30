package br.com.ibridge.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@SequenceGenerator(name = "endereco", sequenceName = "SQ_T_ENDERECO", allocationSize = 1)
public class Endereco {

    @Id
    @Column(name = "END_ID")
    @GeneratedValue(generator = "endereco", strategy = GenerationType.SEQUENCE)
    private int id;

    @NotBlank(message = "O CEP é obrigatório!")
    @Column(name = "END_CEP")
    private String cep;

    @NotBlank(message = "O nome da rua é obrigatório")
    @Column(name = "END_LOGRADOURO", nullable = false)
    private String logradouro;

    @NotBlank(message = "O número é obrigatório")
    @Column(name = "END_NUMERO", nullable = false, length = 4)
    private String numero;

    @NotBlank(message = "O bairro é obrigatório")
    @Column(name = "END_BAIRRO", nullable = false)
    private String bairro;

    @NotBlank(message = "A cidade é obrigatório")
    @Column(name = "END_CIDADE", nullable = false)
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @Column(name = "END_ESTADO", nullable = false)
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
