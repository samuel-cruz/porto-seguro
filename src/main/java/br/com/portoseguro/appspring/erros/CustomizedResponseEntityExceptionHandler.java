package br.com.portoseguro.appspring.erros;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author samuel-cruz
 *
 */
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<RespostaErro> quandoGerarErro(final Exception ex, final WebRequest request) {
        HttpStatus httpCode = ex.getClass().getAnnotation(ResponseStatus.class).value();
        return new ResponseEntity<>(new RespostaErro(ex.getMessage()), httpCode);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        String mensagemDeRetorno = "";
        for (final ObjectError erro : ex.getBindingResult().getAllErrors())
            if (erro instanceof FieldError)
                mensagemDeRetorno = mensagemDeRetorno + ((FieldError) erro).getField() + ": " + erro.getDefaultMessage() + "\n";
            else
                mensagemDeRetorno = mensagemDeRetorno + erro.toString();

        return new ResponseEntity<>(new RespostaErro(mensagemDeRetorno), status);
    }
}