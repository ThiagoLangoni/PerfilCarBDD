package perfilcarbdd.entidade;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfil implements Serializable{

	private static final long serialVersionUID = 1L;
    
    private Long id;
    private String apelido;
    private String marca;
    private String modelo;
    private String quilometragemAtual;
    private String anoFabricacao;
    private String anoModelo;
    private String nomeProprietario;
    private String informacoesAdicionais;
    private String timestamp;
 
}