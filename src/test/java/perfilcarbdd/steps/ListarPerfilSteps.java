package perfilcarbdd.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.es.Dado;
import cucumber.api.java.pt.Entao;
import perfilcarbdd.entidade.*;

public class ListarPerfilSteps {

    private ResponseEntity<String> responseEntity;

    @Dado("Que cliente clicou no botao home")
    public void que_cliente_clicou_no_botao_home() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(HttpStatus statusCode) {
                return false;
            }
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        this.responseEntity = restTemplate.exchange("http://localhost:8080/perfil/perfis", HttpMethod.GET, request,
                String.class);
    }

    @Entao("Todos os perfis serao listados")
    public void todos_os_perfis_serao_listados() {

        ObjectMapper perfilMapper = new ObjectMapper();
        List<Perfil> perfilLista = new ArrayList<>();

        try {
            perfilLista = perfilMapper.readValue(responseEntity.getBody(), new TypeReference<List<Perfil>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertTrue(perfilLista.size() > 0);
    }

}