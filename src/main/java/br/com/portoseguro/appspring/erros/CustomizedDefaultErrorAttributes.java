package br.com.portoseguro.appspring.erros;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * @author samuel-cruz
 *
 */
@Component
public class CustomizedDefaultErrorAttributes extends DefaultErrorAttributes {

    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";

    @Override
    @SuppressWarnings("deprecation")
    public Map<String, Object> getErrorAttributes(final WebRequest webRequest, final boolean includeStackTrace) {
        final Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        final Map<String, Object> out = new HashMap<>();
        out.put("mensagem", map.get("message"));
        out.put("url", map.get("path"));

        return out;
    }

    @Override
    public Throwable getError(final WebRequest webRequest) {
        Throwable exception = recuperarAtributoPeloNome(webRequest, ERROR_ATTRIBUTE);
        if (exception == null)
            exception = recuperarAtributoPeloNome(webRequest, "javax.servlet.error.exception");
        return exception;
    }

    @SuppressWarnings("unchecked")
    private <T> T recuperarAtributoPeloNome(final RequestAttributes listaDeAtributos, final String nome) {
        return (T) listaDeAtributos.getAttribute(nome, RequestAttributes.SCOPE_REQUEST);
    }

}