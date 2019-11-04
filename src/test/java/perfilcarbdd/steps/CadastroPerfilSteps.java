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


public class CadastroPerfilSteps {

    private String requestJson;
    private ResponseEntity<Perfil> responseEntity;

    @Dado("Que cliente preencheu o cadastro do perfil do seu carro")
    public void que_cliente_preencheu_o_cadastro_do_perfil_do_seu_carro(io.cucumber.datatable.DataTable dataTable) {

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

    @Quando("clicar em cadastrar")
    public void clica_em_cadastrar() {
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

        this.responseEntity = restTemplate.exchange("http://localhost:8080/perfil/perfil", HttpMethod.POST, request,
                Perfil.class);
    }

    @Entao("O cadastro tem que ser concluido com sucesso: {string} e o apelido igual {string}")
    public void o_cadastro_tem_que_ser_concluido_com_sucesso_e_o_apelido_igual(String status, String apelido) {
        assertEquals(this.responseEntity.getStatusCode(), HttpStatus.valueOf(status));
        assertEquals(this.responseEntity.getBody().getApelido(), apelido);
    }

}