package br.com.portoseguro.appspring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author samuel-cruz
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTeste {

    protected @Autowired
    WebApplicationContext context;
    protected MockMvc mvc;

    @org.junit.Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    protected ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    protected String converterObjetoParaString(final Object obj) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(obj);
    }

    protected <T> T converterStringParaObjeto(final String obj, final Class<T> classe) {
        try {
            return getObjectMapper().readValue(obj, classe);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}