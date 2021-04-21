package br.com.portoseguro.appspring.exer5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author samuel-cruz
 *
 */
public class ListaTeste {
    @Test
    void main() {
        retornarNumerosPares(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))
                .forEach(System.out::println);
    }

    private List<Integer> retornarNumerosPares(List<Integer> listaNumerica) {
        return listaNumerica
                .stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());
    }

}
