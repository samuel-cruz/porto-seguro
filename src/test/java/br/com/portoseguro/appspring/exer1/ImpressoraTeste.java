package br.com.portoseguro.appspring.exer1;

import org.junit.jupiter.api.Test;

/**
 * @author samuel-cruz
 */
public class ImpressoraTeste {
    @Test
    void main() {
        Impressora impressora = conteudo -> {
            System.out.println(conteudo);
        };

        impressora.imprimir("Java - Interface funcional");
    }
}
