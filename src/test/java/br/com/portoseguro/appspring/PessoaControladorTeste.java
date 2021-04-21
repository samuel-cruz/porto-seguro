package br.com.portoseguro.appspring;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.portoseguro.appspring.entidades.Endereco;
import br.com.portoseguro.appspring.entidades.Pessoa;
import org.hibernate.validator.constraints.br.CPF;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @author samuel-cruz
 *
 */
public class PessoaControladorTeste extends BaseTeste {
    private static String BASE_URL = "/pessoas/";

    @Test
    public void deveRetornar400QuandoNomeNaoInformado() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();

        pessoa.setNome(null);
        deveGerarErroAoChamarPost(pessoa, "nome: não deve estar em branco");
    }

    @Test
    public void deveRetornar400QuandoNomeConterMaisDe30Caracteres() throws Exception {
        final Pessoa nome = gerarNovaPessoa();

        nome.setNome("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        deveGerarErroAoChamarPost(nome, "nome: tamanho deve ser entre 0 e 30");
    }

    @Test
    public void deveRetornar400QuandoIdadeNaoInformado() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();

        pessoa.setIdade(null);
        deveGerarErroAoChamarPost(pessoa, "idade: não deve ser nulo");
    }

    @Test
    public void deveRetornar400QuandoIdadeForZero() throws Exception {
        final Pessoa nome = gerarNovaPessoa();

        nome.setIdade(0);
        deveGerarErroAoChamarPost(nome, "idade: deve ser maior que 0");
    }

    @Test
    public void deveRetornar400QuandoIdadeForNegativa() throws Exception {
        final Pessoa nome = gerarNovaPessoa();

        nome.setIdade(-1);
        deveGerarErroAoChamarPost(nome, "idade: deve ser maior que 0");
    }

    @Test
    public void deveRetornar400QuandoCPFNaoInformado() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();

        pessoa.setCpf(null);
        deveGerarErroAoChamarPost(pessoa, "cpf: não deve estar em branco");
    }

    public void deveRetornar400QuandoCPFConterMaisDe11Caracteres() throws Exception {
        final Pessoa nome = gerarNovaPessoa();

        nome.setNome("012345678912");
        deveGerarErroAoChamarPost(nome, "cpf: tamanho deve ser entre 0 e 11");
    }

    @Test
    public void deveRetornar400QuandoCPFForInvalido() throws Exception {
        final Pessoa dto = gerarNovaPessoa();

        dto.setCpf("123456789");
        deveGerarErroAoChamarPost(dto, "cpf: O CPF informado é inválido");
    }

    @Test
    public void deveRetornar400QuandoCPFJaExistirNaBase() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();
        pessoa.setCpf("80894611070");

        gravarPessoaERetornar(pessoa);
        deveGerarErroAoChamarPost(pessoa, "Cliente já existe!");
    }

    @Test
    public void deveRetornar400QuandoEnderecoNaoInformado() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();

        pessoa.setEnderecos(null);
        deveGerarErroAoChamarPost(pessoa, "enderecos: não deve estar vazio");
    }

    @Test
    public void deveRetornar400QuandoEnderecoInformadoVazio() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();
        pessoa.setEnderecos(new ArrayList<>());
        deveGerarErroAoChamarPost(pessoa, "enderecos: não deve estar vazio");
    }

    @Test
    public void deveRetornar400QuandoLogradouroNãoInformado() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();
        pessoa.getEnderecos().get(0).setLogradouro(null);
        deveGerarErroAoChamarPost(pessoa, "enderecos[0].logradouro: não deve estar em branco");
    }

    public void deveRetornar400QuandoLogradouroConterMaisDe40Caracteres() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();

        pessoa.getEnderecos().get(0).setLogradouro("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        deveGerarErroAoChamarPost(pessoa, "enderecos[0].logradouro: tamanho deve ser entre 0 e 40");
    }

    public void deveRetornar400QuandoComplementoConterMaisDe50Caracteres() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();

        pessoa.getEnderecos().get(0).setComplemento("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        deveGerarErroAoChamarPost(pessoa, "enderecos[0].complemento: tamanho deve ser entre 0 e 50");
    }

    @Test
    public void deveRetornar400QuandoCidadeNãoInformado() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();
        pessoa.getEnderecos().get(0).setCidade(null);
        deveGerarErroAoChamarPost(pessoa, "enderecos[0].cidade: não deve estar em branco");
    }

    public void deveRetornar400QuandoCidadeConterMaisDe45Caracteres() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();

        pessoa.getEnderecos().get(0).setCidade("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        deveGerarErroAoChamarPost(pessoa, "enderecos[0].cidade: tamanho deve ser entre 0 e 45");
    }

    @Test
    public void deveRetornar400QuandoEstadoNãoInformado() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();
        pessoa.getEnderecos().get(0).setEstado(null);
        deveGerarErroAoChamarPost(pessoa, "enderecos[0].estado: não deve estar em branco");
    }
    public void deveRetornar400QuandoEstadoConterMenosDe2Caracteres() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();

        pessoa.getEnderecos().get(0).setEstado("A");
        deveGerarErroAoChamarPost(pessoa, "enderecos[0].estado: tamanho deve ser entre 2 e 2");
    }

    public void deveRetornar400QuandoEstadoConterMaisDe2Caracteres() throws Exception {
        final Pessoa pessoa = gerarNovaPessoa();

        pessoa.getEnderecos().get(0).setEstado("ABC");
        deveGerarErroAoChamarPost(pessoa, "enderecos[0].estado: tamanho deve ser entre 2 e 2");
    }

    @Test
    public void deveRetornar201QuandoCadastrarPessoa() throws Exception {
        mvc
            .perform(post(BASE_URL).content(converterObjetoParaString(gerarNovaPessoa())).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated()).andReturn();
    }

    public void deveRetornarMesmoObjetoPersistido() throws Exception {
        final Pessoa pessoaEntrada = gerarNovaPessoa();
        pessoaEntrada.setCpf("67836377008");
        final Pessoa pessoaRetorno = gravarPessoaERetornar(pessoaEntrada);

        assertNotNull(pessoaRetorno);
        assertEquals(pessoaEntrada.getNome(), pessoaRetorno.getNome());
        assertEquals(pessoaEntrada.getIdade(), pessoaRetorno.getIdade());
        assertEquals(pessoaEntrada.getCpf(), pessoaRetorno.getCpf());

        assertNotNull(pessoaRetorno.getEnderecos());
        assertEquals(pessoaEntrada.getEnderecos().size(), pessoaRetorno.getEnderecos().size());

        for(int indexEndereco = 0; indexEndereco < pessoaRetorno.getEnderecos().size(); indexEndereco++) {
            Endereco enderecoEntrada = pessoaEntrada.getEnderecos().get(indexEndereco);
            Endereco enderecoRetorno = pessoaRetorno.getEnderecos().get(indexEndereco);

            assertEquals(enderecoEntrada.getLogradouro(), enderecoRetorno.getLogradouro());
            assertEquals(enderecoEntrada.getComplemento(), enderecoRetorno.getComplemento());
            assertEquals(enderecoEntrada.getCidade(), enderecoRetorno.getCidade());
            assertEquals(enderecoEntrada.getEstado(), enderecoRetorno.getEstado());
        }
    }

    @Test
    public void deveRetornar204QuandoNaoExistirPessoaComCPFCadastrado() throws Exception {
        mvc.perform(get(BASE_URL.concat("95706050066"))).andExpect(status().isNoContent());
    }

    private void deveGerarErroAoChamarPost(final Pessoa dto, final String erroEsperado) throws Exception {
        mvc.perform(post(BASE_URL).content(converterObjetoParaString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", containsString(erroEsperado)));
    }

    private Pessoa gravarPessoaERetornar(final Pessoa pessoa) throws JsonProcessingException, Exception {
        mvc
            .perform(post(BASE_URL).content(converterObjetoParaString(pessoa)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        final MvcResult mvcResult = mvc.perform(get(BASE_URL.concat(pessoa.getCpf()))).andExpect(status().isOk()).andReturn();
        return converterStringParaObjeto(mvcResult.getResponse().getContentAsString(), Pessoa.class);
    }

    private Pessoa gerarNovaPessoa() {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Javanilson da Cruz");
        pessoa.setIdade(25);
        pessoa.setCpf("00006400000");
        pessoa.setEnderecos(Arrays.asList(
                new Endereco("Rua James Gosling", "1995", "Green Project", "GP"),
                new Endereco("Avenida Rod Johnson", null, "Spring Framework", "SF")
        ));

        return pessoa;
    }
}