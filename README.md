
# Microsserviço de Transações – Projeto Final Integrado (5º Semestre)

Este repositório faz parte do **Projeto Final Integrado** do curso de **Tecnologia em Análise e Desenvolvimento de Sistemas – IFSP**. O projeto foi desenvolvido nas disciplinas:

- PTBDAMS – Desenvolvimento de APIs e Microsserviços  
- PTBADOP – Ambiente de Desenvolvimento e Operações (DevOps)  
- PTBDDMA – Desenvolvimento para Dispositivos Móveis  

## Tema do Projeto: Gestão Financeira Pessoal

O sistema tem como objetivo fornecer aos usuários um meio eficiente de gerenciar seus gastos diários, através do cadastro, categorização e acompanhamento de transações financeiras.

O projeto está estruturado em três microsserviços principais:

- Microsserviço de Usuários (responsável por autenticação e dados pessoais)
- Microsserviço de Categorias (responsável pela classificação de despesas)
- Microsserviço de Transações (**este repositório**)

---

## Equipe de Desenvolvimento

| Parte do Projeto                 | Integrante    |
|--------------------------------- |-------------- |
| Microsserviço de Transações       | Ana           |
| Microsserviço de Usuários         | Gabriele      |
| Microsserviço de Categorias       | Letícia       |
| Aplicativo Flutter (Frontend)     | Matheus       |
| DevOps / Deploy / Documentação    | Jhonatan      |

---

## Microsserviço de Transações

Este microsserviço é responsável pelo gerenciamento de todas as **transações financeiras** registradas pelos usuários.

### Estrutura dos Dados (Exemplo de Payload)

```json
{
  "id": 1,
  "valor": 250.75,
  "data": "2025-06-21",
  "descricao": "Pagamento de aluguel",
  "user_id": 3,
  "categoria_id": 2
}
````

### Funcionalidades Principais

* Cadastro de transações
* Edição de transações
* Listagem de transações
* Exclusão de transações
* Integração com os microsserviços de Usuários e Categorias
* Comunicação assíncrona utilizando RabbitMQ
* Documentação automática via Swagger/OpenAPI

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3.x
* PostgreSQL
* RabbitMQ
* Swagger / OpenAPI
* Docker
* GitHub Actions (CI/CD)

---

## Como Executar Localmente

### Pré-requisitos

* Java 17 instalado
* Docker e Docker Compose
* PostgreSQL rodando localmente
* RabbitMQ executando via Docker:

```bash
docker run -d --hostname rabbitmq-local --name rabbitmq \
  -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

### Configuração do `application.properties`

```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

spring.datasource.url=jdbc:postgresql://localhost:5432/transacoes
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### Execução do Projeto

```bash
./mvnw spring-boot:run
```

### Acesso à Documentação Swagger

* [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Endpoints Disponíveis

| Método | Endpoint           | Descrição                         |
| ------ | ------------------ | --------------------------------- |
| GET    | `/transacoes`      | Listar todas as transações        |
| POST   | `/transacoes`      | Criar uma nova transação          |
| PUT    | `/transacoes/{id}` | Atualizar uma transação existente |
| DELETE | `/transacoes/{id}` | Excluir uma transação             |

---

## Integração com RabbitMQ

O microsserviço de Transações consome mensagens da fila `categorias.fila`, enviadas pelo microsserviço de Categorias.

### Exemplo de Listener:

```java
@RabbitListener(queues = "categorias.fila")
public void receberMensagem(String mensagem) {
    System.out.println("Mensagem recebida da fila de categorias: " + mensagem);
}
```

---

## Deploy Automatizado (CI/CD)

O processo de deploy será realizado de forma automatizada via **GitHub Actions**, com publicação contínua para a nuvem (Render, Railway ou Heroku).

* Arquivo de configuração da pipeline: `.github/workflows/deploy.yml` (em desenvolvimento)

---

## Documentação Complementar

* Documentação Swagger ativa e funcional
* Integração com RabbitMQ implementada
* Comunicação REST entre microsserviços concluída
* Evidências de testes via Postman (disponíveis na pasta `/docs`)
* Registro de etapas de deploy (em andamento)

```


