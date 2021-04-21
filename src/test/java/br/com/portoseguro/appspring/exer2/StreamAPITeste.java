package br.com.portoseguro.appspring.exer2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author samuel-cruz
 *
 */
public class StreamAPITeste {
    @Test
    void main() {
        List<Integer> listaCodigo = Arrays.asList(1, 2, 3, 5, 8, 13, 21, 34, 55, 89);

        listaCodigo.forEach(System.out::println);
    }
}
