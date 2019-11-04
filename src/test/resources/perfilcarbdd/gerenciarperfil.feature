# language: pt
@GerenciarPerfil
Funcionalidade: Gerenciar um perfil no site
  Funcionalidade de gerenciar um perfil no site

  @finaliza_cadastro
  Esquema do Cenario: Cliente cadastra perfil
    Dado Que cliente preencheu o cadastro do perfil do seu carro
    | apelido    | marca  | modelo  | quilometragemAtual | anoFabricacao | anoModelo | nomeProprietario | informacoesAdicionais | timestamp                     |
    | Mustang  | ford   | mustang | 1000               | 2020          | 2020      | Eduardo          | bunitu dmais          | timestamp 2019-11-02-19.41.55 |
    Quando clicar em cadastrar
    Entao O cadastro tem que ser concluido com sucesso: <statusCode> e o apelido igual <apelido>
    Exemplos:
    | id | apelido   | marca  | modelo  | quilometragemAtual | anoFabricacao | anoModelo | nomeProprietario | informacoesAdicionais | timestamp                     | statusCode |
    | 1  | "Mustang" | ford   | mustang | 1000               | 2020          | 2020      | Eduardo          | bunitu dmais          | timestamp 2019-11-02-19.41.55 | "OK"       |

  @finaliza_atualizacao_cadastro
  Esquema do Cenario: Cliente atualiza perfil
    Dado Que cliente alterou o cadastro do perfil do seu carro
    | id   | apelido    | marca  | modelo  | quilometragemAtual | anoFabricacao | anoModelo | nomeProprietario | informacoesAdicionais | timestamp                     |
    | 1    | Mustang    | ford   | mustang | 2000               | 2020          | 2020      | Eduardo          | bunitu dmais          | timestamp 2019-11-03-19.41.55 |
    Quando clicar em atualizar <id>
    Entao A atualizacao tem que ser concluida com sucesso: <statusCode> e a quilometragem Atual igual <quilometragemAtual>
    Exemplos:
    | id   | apelido   | marca  | modelo  | quilometragemAtual | anoFabricacao | anoModelo | nomeProprietario | informacoesAdicionais | timestamp                     | statusCode |
    | "1"  | "Mustang" | ford   | mustang | "2000"             | 2020          | 2020      | Eduardo          | bunitu dmais          | timestamp 2019-11-03-19.41.55 | "OK"       |  

  @carregar_lista_de_perfis
  Esquema do Cenario: Cliente quer ver todos perfis cadastrados
    Dado Que cliente clicou no botao home
    Entao Todos os perfis serao listados
    Exemplos:
    | id   | apelido    | marca  | modelo  | quilometragemAtual | anoFabricacao | anoModelo | nomeProprietario  | informacoesAdicionais    | timestamp                     |
    | "1"  | "Mustang"  | ford   | mustang | "2001"             | 2020          | 2020      | Eduardo           | bunitu dmais             | timestamp 2019-11-03-19.41.55 |
    | "2"  | "Mustang1" | ford   | mustang | "2002"             | 2020          | 2020      | Eduardo1          | bunitu mesmo             | timestamp 2019-11-03-19.41.55 |
    | "3"  | "Mustang1" | ford   | mustang | "2003"             | 2020          | 2020      | Eduardo2          | bunitu pcaramba          | timestamp 2019-11-03-19.41.55 |

  @finaliza_exclusao_cadastro
  Esquema do Cenario: Cliente exclui perfil
    Dado Que cliente quer excluir o perfil do seu carro
    | id   | apelido    | marca  | modelo  | quilometragemAtual | anoFabricacao | anoModelo | nomeProprietario | informacoesAdicionais | timestamp                     |
    | 1    | Mustang    | ford   | mustang | 2000               | 2020          | 2020      | Eduardo          | bunitu dmais          | timestamp 2019-11-03-19.41.55 |
    Quando clicar em excluir <id>
    Entao A exclusão tem que ser concluida com sucesso: <statusCode>
    Exemplos:
    | statusCode |
    | "OK"       |