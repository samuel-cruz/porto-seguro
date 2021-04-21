package br.com.portoseguro.appspring.repositorios;

import br.com.portoseguro.appspring.entidades.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author samuel-cruz
 *
 */
@Repository
public class PessoaRepositorio {
    private Map<String, Pessoa> baseDePessoa;

    public PessoaRepositorio() {
        baseDePessoa = new HashMap<>();
    }

    public void salvar(final Pessoa pessoa) {
        if(!baseDePessoa.containsKey(pessoa.getCpf())) {
            baseDePessoa.put(pessoa.getCpf(), pessoa);
        }
    }

    public Optional<Pessoa> buscarPorCPF(final String cpf) {
        return Optional.ofNullable(baseDePessoa.get(cpf));
    }
}
