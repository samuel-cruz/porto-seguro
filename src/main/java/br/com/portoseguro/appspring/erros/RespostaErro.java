package br.com.portoseguro.appspring.erros;

/**
 * @author samuel-cruz
 *
 */
public class RespostaErro {

    private final String message;

    public RespostaErro(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}