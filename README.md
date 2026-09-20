# restapi-complete

API REST de pessoas, livros e arquivos, com autenticação JWT e um client React.

## Status

Estudo. Não está em produção.

## Para que serve

Exercício de API REST completa: CRUD de pessoas e livros, upload de arquivos, autenticação JWT, HATEOAS e documentação OpenAPI. O front em React consome essa API. Serve para praticar o caminho de uma API Spring até o browser, com banco, migrações e testes de integração.

## Stack

- Java 18 · Spring Boot 3 · Spring Security · JWT
- JPA · Flyway · MySQL · HATEOAS · Springdoc OpenAPI
- Testcontainers · REST Assured · Docker
- React 17 (pasta `client/`)

## Como executar

Pré-requisitos: Java 18+, Maven, Node.js, Docker (para o banco).

Configure o MySQL com as variáveis do `docker-compose.yml` (`MYSQL_DATABASE`, `MYSQL_ROOT_PASSWORD`, usuário da aplicação). Não commite senha no repositório — use o que estiver no seu ambiente.

```bash
git clone https://github.com/mateusaledev/restapi-complete.git
cd restapi-complete
docker compose up -d db
cd app && ./mvnw spring-boot:run
```

Client:

```bash
cd client
npm install
npm start
```

- API: Swagger da aplicação depois que o `app` subir
- Front: `http://localhost:3000`

Testes do backend: `cd app && ./mvnw test`

## O que faz

- Autenticação (`AuthController`)
- CRUD de pessoas e livros, com HATEOAS
- Upload/download de arquivos
- Conteúdo em JSON, XML ou YAML
- Documentação OpenAPI no Springdoc

## Estrutura

```
app/                 backend Spring Boot
client/              React (Create React App)
docker-compose.yml   MySQL + app
```
