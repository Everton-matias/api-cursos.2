# API de Cursos - Atividade Prática Integrada

## 1. Fluxo Cliente e Servidor
Quando um cliente consulta `GET /cursos`, o seguinte fluxo acontece:
`Cliente -> requisição HTTP -> back-end -> resposta HTTP -> cliente`
*   **Cliente:** Quem faz a solicitação (ex: navegador, Postman ou Insomnia).
*   **Servidor (Back-end):** A aplicação Spring Boot que recebe a solicitação.
*   **O que é solicitado:** O cliente envia um `request` pedindo a lista completa de cursos.
*   **Processamento e Resposta:** O servidor acessa o banco de dados via Repository, recupera os cursos, os formata em JSON e devolve no `response` com o status `200 OK`.

## 2. Responsabilidades do Back-end no Cadastro de Curso
Ao receber um cadastro via `POST /cursos`, o back-end executa as seguintes ações:
1. Recebe os dados em formato JSON enviados pelo cliente;
2. Transforma (desserializa) esses dados em um objeto da classe Java `Curso`;
3. Verifica as regras de negócio no Service (nome não vazio, carga horária > 0);
4. Salva o curso no banco de dados PostgreSQL usando o Repository;
5. Devolve uma resposta HTTP ao cliente (status `201 Created` e o objeto salvo).

## 3. Contrato da API (Endpoints)
Abaixo estão os endpoints disponíveis nesta API:

| Método | Endpoint       | Operação                  | Resposta Esperada                   |
|--------|----------------|---------------------------|-------------------------------------|
| GET    | /cursos        | Listar todos os cursos    | 200 OK                              |
| GET    | /cursos/{id}   | Buscar um curso pelo ID   | 200 OK ou 404 Not Found             |
| POST   | /cursos        | Cadastrar um curso        | 201 Created                         |
| PUT    | /cursos/{id}   | Atualizar um curso        | 200 OK ou 404 Not Found             |
| DELETE | /cursos/{id}   | Remover um curso          | 204 No Content ou 404 Not Found     |

## 4. Estrutura e Configuração do Projeto
*   **pom.xml:** Arquivo do Maven usado para gerenciar as dependências do projeto (como Spring Web, Data JPA e PostgreSQL Driver).
*   **src/main/java:** Diretório onde fica todo o código-fonte Java, organizado em pacotes (Controller, Service, Model, Repository).
*   **src/main/resources:** Diretório que guarda recursos não-Java, como arquivos de propriedades e templates.
*   **Classe com @SpringBootApplication:** É a classe principal que inicializa o servidor Tomcat embutido e sobe a aplicação.
*   **application.properties:** Arquivo para configurar variáveis globais, como a porta do servidor, nome do app e as credenciais de conexão com o banco de dados PostgreSQL.