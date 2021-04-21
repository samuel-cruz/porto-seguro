package br.com.portoseguro.appspring.entidades;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @author samuel-cruz
 *
 */
public class Pessoa {
    @NotBlank
    @Size(max = 30)
    private String nome;
    @NotNull
    @Positive
    private Integer idade;
    @NotBlank
    @Size(max = 11)
    @CPF(message = "O CPF informado é inválido")
    private String cpf;
    @NotEmpty
    @Valid
    private List<Endereco> enderecos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
