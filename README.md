# 🧾 Microsserviço de Transações – Projeto Final Integrado (5º Semestre)

Este repositório faz parte do **Projeto Final Integrado** do curso de **Tecnologia em Análise e Desenvolvimento de Sistemas (IFSP)**, desenvolvido para as disciplinas:

- PTBDAMS – Desenvolvimento de APIs e Microsserviços
- PTBADOP – Ambiente de Desenvolvimento e Operações (DevOps)
- PTBDDMA – Desenvolvimento para Dispositivos Móveis

## 📱 Tema do Projeto: Gestão Financeira Pessoal

O sistema tem como objetivo auxiliar usuários a gerenciarem seus gastos diários, permitindo o cadastro de transações e categorização de despesas. O projeto é dividido em três microsserviços:

- Usuários (autenticação e dados pessoais)
- Categorias (descrição de tipos de gastos)
- Transações (**este repositório**)

---

## 👩‍💻 Responsáveis

| Parte do Projeto                     | Integrante    |
|-------------------------------------|---------------|
| Microsserviço de Transações (Spring Boot) | Ana           |
| Microsserviço de Categorias         | Letícia       |
| Aplicativo Flutter                  | Matheus       |
| DevOps / Deploy e Documentação      | Jhonatan      |

---

## 🧩 Microsserviço de Transações

Este serviço é responsável por gerenciar as **transações financeiras** dos usuários.

### 🔗 Estrutura dos Dados

```json
{
  "id": 1,
  "valor": 250.75,
  "data": "2025-06-21",
  "descricao": "Pagamento de aluguel",
  "user_id": 3,
  "categoria_id": 2
}
🔄 Funcionalidades
Cadastro, edição, listagem e remoção de transações (CRUD)

Integração com microsserviços de Usuários e Categorias

Comunicação assíncrona com RabbitMQ (fila: categorias.fila)

Documentação Swagger

⚙️ Tecnologias Utilizadas
Java 17

Spring Boot 3.x

PostgreSQL

RabbitMQ

Swagger/OpenAPI

Docker

GitHub Actions (CI/CD)

📦 Como executar localmente
Pré-requisitos:
Docker e Docker Compose

Java 17

PostgreSQL rodando localmente

RabbitMQ rodando via Docker:

bash
Copiar
Editar
docker run -d --hostname rabbitmq-local --name rabbitmq \
  -p 5672:5672 -p 15672:15672 rabbitmq:3-management
Configuração do application.properties:
properties
Copiar
Editar
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

spring.datasource.url=jdbc:postgresql://localhost:5432/transacoes
spring.datasource.username=postgres
spring.datasource.password=postgres
Rodando o projeto:
bash
Copiar
Editar
./mvnw spring-boot:run
Acesse a documentação Swagger:
👉 http://localhost:8080/swagger-ui/index.html

🧪 Endpoints disponíveis
Método	Endpoint	Descrição
GET	/transacoes	Listar transações
POST	/transacoes	Criar nova transação
PUT	/transacoes/{id}	Atualizar transação
DELETE	/transacoes/{id}	Remover transação

📡 Integração com RabbitMQ
Mensagens são consumidas da fila categorias.fila, enviada pelo microsserviço de Categorias.

Listener configurado em:

java
Copiar
Editar
@RabbitListener(queues = "categorias.fila")
public void receberMensagem(String mensagem) {
    System.out.println("Mensagem recebida da fila de categorias: " + mensagem);
}
🚀 Deploy Automatizado
O deploy será realizado via GitHub Actions com deploy contínuo para a nuvem (ex: Render, Railway ou Heroku).

Arquivo de configuração da pipeline: .github/workflows/deploy.yml (em construção)

📚 Documentação complementar
 Swagger ativo e funcional

 Mensageria com RabbitMQ implementada

 Integração REST com outros microsserviços

 Prints dos testes no Postman (ver pasta /docs)

 Print do deploy funcionando (em andamento)

