package br.com.portoseguro.appspring.exer3;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author samuel-cruz
 *
 */
public class OptionalTeste {
    @Test
    void main() {
        retornarOProprioValorDeEntrada("Primeira chamada").ifPresent(System.out::println);
        retornarOProprioValorDeEntrada(null).ifPresent(System.out::println);
        retornarOProprioValorDeEntrada("Terceira chamada").ifPresent(System.out::println);
    }

    private Optional<String> retornarOProprioValorDeEntrada(String entrada) {
        return Optional.ofNullable(entrada);
    }
}
