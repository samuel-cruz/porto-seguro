package br.com.portoseguro.appspring.exer4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author samuel-cruz
 *
 */
public class StringTeste {
    @Test
    void deveRetornarQueAPrimeiraLetraEMaiuscula() {
        tipoDaPrimeiraLetra("Banana").ifPresent(valor -> Assertions.assertEquals("Maiúscula", valor));
    }

    @Test
    void deveRetornarQueAPrimeiraLetraEMinuscula() {
        tipoDaPrimeiraLetra("bananinha").ifPresent(valor -> Assertions.assertEquals("minúscula", valor));
    }

    @Test
    void deveRetornarOptionalEmpty() {
        Assertions.assertEquals(Optional.empty(), tipoDaPrimeiraLetra(null));
        Assertions.assertEquals(Optional.empty(), tipoDaPrimeiraLetra("     "));
    }

    private Optional<String> tipoDaPrimeiraLetra(String entrada) {
        if(entrada == null || entrada.trim().isEmpty())
            return Optional.empty();

        String primeiraLetra = entrada.substring(0, 1);
        if(primeiraLetra.equals(primeiraLetra.toUpperCase())) {
            return Optional.of("Maiúscula");
        } else {
            return Optional.of("minúscula");
        }
    }
}
