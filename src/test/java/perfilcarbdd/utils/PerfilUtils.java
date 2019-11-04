package perfilcarbdd.utils;

import java.util.List;
import java.util.Map;

import perfilcarbdd.entidade.Perfil;

public class PerfilUtils {

    public static Perfil transformListinObject(List<Map<String, String>> list) {
        Perfil perfil = new Perfil();
        if (list.get(0).get("id") != null) {
            perfil.setId(Long.parseLong(list.get(0).get("id")));
        }
        perfil.setApelido(list.get(0).get("apelido"));
        perfil.setMarca(list.get(0).get("marca"));
        perfil.setModelo(list.get(0).get("modelo"));
        perfil.setQuilometragemAtual(list.get(0).get("quilometragemAtual"));
        perfil.setAnoFabricacao(list.get(0).get("anoFabricacao"));
        perfil.setAnoModelo(list.get(0).get("anoModelo"));
        perfil.setNomeProprietario(list.get(0).get("nomeProprietario"));
        perfil.setInformacoesAdicionais(list.get(0).get("informacoesAdicionais"));
        perfil.setTimestamp(list.get(0).get("timestamp"));
        return perfil;
    }

}