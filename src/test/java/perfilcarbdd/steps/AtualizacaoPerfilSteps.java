package perfilcarbdd.steps;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import cucumber.api.java.pt.Quando;
import perfilcarbdd.entidade.Perfil;
import perfilcarbdd.utils.PerfilUtils;

public class AtualizacaoPerfilSteps {

    private String requestJson;
    private ResponseEntity<Perfil> responseEntity;

    @Dado("Que cliente alterou o cadastro do perfil do seu carro")
    public void que_cliente_alterou_o_cadastro_do_perfil_do_seu_carro(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        Perfil perfil = PerfilUtils.transformListinObject(list);

        ObjectMapper perfilMapper = new ObjectMapper();
        this.requestJson = "";
        try {
            requestJson = perfilMapper.writerWithDefaultPrettyPrinter().writeValueAsString(perfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Quando("clicar em atualizar {string}")
    public void clicar_em_atualizar(String id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(HttpStatus statusCode) {
                return false;
            }
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

        this.responseEntity = restTemplate.exchange("http://localhost:8080/perfil/perfil/", HttpMethod.PUT, request,
                Perfil.class);
    }

    @Entao("A atualizacao tem que ser concluida com sucesso: {string} e a quilometragem Atual igual {string}")
    public void a_atualizacao_tem_que_ser_concluida_com_sucesso_e_a_quilometragem_Atual_igual(String status,
            String quilometragem) {
        assertEquals(this.responseEntity.getStatusCode(), HttpStatus.valueOf(status));
        assertEquals(this.responseEntity.getBody().getQuilometragemAtual(), quilometragem);
    }

}