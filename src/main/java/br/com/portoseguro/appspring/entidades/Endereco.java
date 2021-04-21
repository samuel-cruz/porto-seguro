package br.com.portoseguro.appspring.entidades;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author samuel-cruz
 *
 */
public class Endereco {
    @NotBlank
    @Size(max = 40)
    private String logradouro;
    @Size(max = 50)
    private String complemento;
    @NotBlank
    @Size(max = 45)
    private String cidade;
    @NotBlank
    @Size(min = 2, max = 2)
    private String estado;

    public Endereco() {

    }

    public Endereco(final String logradouro, final String complemento, final String cidade, final String estado) {
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(final String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(final String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(final String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }
}
