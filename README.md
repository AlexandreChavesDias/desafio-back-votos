# desafio-back-votos
Desafio Backend South System
## 
## Arquitetura
Foi utilizada uma arquitetura em camadas para separar as responsabilidades, sendo as mesmas:
- SERVICE: Compreendendo a lógica de negócio.
- CONTROLLER: Para comunicação externa.
- REPOSITORY: Para recuperação e persistência dos dados.
- ENTITY: Para as classes de modelo.
- DTOs: Para trafegar e modelar as respostas.

## Observações
- Para conversão dos DTOs optei por utilizar a lib MODELMAPPER, que abstrai essa lógica deixando o código mais legível.
- Para as classes da camada de modelo optei por utillizar  a lib LOMBOK, neste contexto não vi problemas em abrir mão do controle da geração dos Getters e Setters.
- Versionamento foi utilizada a propria URL da API "api/v1/".

## Ferammentas
- Para o desenvolvimento da API: Java 11, Spring Framework e banco de dados PostgreSQL.
- Documentação da API utilizando Swagger
