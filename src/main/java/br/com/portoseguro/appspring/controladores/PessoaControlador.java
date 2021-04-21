package br.com.portoseguro.appspring.controladores;

import br.com.portoseguro.appspring.entidades.Pessoa;
import br.com.portoseguro.appspring.erros.RespostaErro;
import br.com.portoseguro.appspring.repositorios.PessoaRepositorio;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author samuel-cruz
 *
 */
@RestController
@RequestMapping("/pessoas")
public class PessoaControlador {
    private PessoaRepositorio pessoaRepositorio;

    public PessoaControlador(final PessoaRepositorio pessoaRepositorio) {
        this.pessoaRepositorio = pessoaRepositorio;
    }

    @PostMapping()
    public ResponseEntity<?> incluirPessoa(@RequestBody @Valid final Pessoa pessoa) throws Exception {
        if(pessoaRepositorio.buscarPorCPF(pessoa.getCpf()).isPresent())
            return ResponseEntity.badRequest().body(new RespostaErro("Cliente já existe!"));

        pessoaRepositorio.salvar(pessoa);

        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Pessoa> listarPessoa(@PathVariable final String cpf) {
        Optional<Pessoa> pessoa = pessoaRepositorio.buscarPorCPF(cpf);

        if(pessoa.isPresent())
            return ResponseEntity.ok(pessoa.get());

        return  ResponseEntity.noContent().build();
    }
}
