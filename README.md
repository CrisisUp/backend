# 🛒 Backend da API do Mercado Fictício
Este é o backend da API para um sistema de gerenciamento de mercado fictício, construído com **Java e Spring Boot**. Ele foi projetado para simular as operações essenciais de um mercado, incluindo gestão de produtos, fornecedores, controle de estoque, registro de compras e vendas, e um módulo de fluxo de caixa para análise financeira.

---
## 🚀 Funcionalidades Principais
* **Gestão de Fornecedores:** CRUD (Create, Read, Update, Delete) completo para cadastro e controle de fornecedores.
* **Gestão de Produtos:** CRUD completo para produtos, com informações de preço de custo, preço de venda e controle de estoque.
* **Gestão de Compras:** Registro de entrada de produtos, que automaticamente atualiza o estoque e gera despesas no fluxo de caixa.
* **Gestão de Vendas:** Registro de saída de produtos, que automaticamente diminui o estoque e gera receitas no fluxo de caixa. Inclui validação básica de estoque mínimo.
* **Gestão de Fluxo de Caixa:** Registro de movimentações financeiras avulsas (receitas e despesas), listagem detalhada por período e cálculo do saldo financeiro.
---
## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 23 (OpenJDK)
* **Framework:** Spring Boot (v3.5.3)
* **Banco de Dados:** PostgreSQL (gerenciado via Docker)
* **Gerenciador de Dependências:** Apache Maven
* **Ambiente de Desenvolvimento:** WSL 2 (Ubuntu 24.04) no Windows 11
* **IDE:** IntelliJ IDEA Community Edition
* **Controle de Versão:** Git & GitHub
---
## ⚙️ Como Configurar e Rodar o Projeto
Siga estas instruções para configurar e executar o backend em seu ambiente local.

### Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas:
* **Java Development Kit (JDK) 23**
* **Docker Desktop** (com integração WSL 2 ativada)
* **Apache Maven** (instalado globalmente ou via Maven Wrapper incluído no projeto)
* **Git**
* **IntelliJ IDEA** (ou outra IDE de sua preferência)
* **Cliente REST** para testar a API (e.g., Postman, Insomnia)

### 1. Clonar o Repositório
Abra seu terminal WSL (Ubuntu) e execute:
```bash
git clone [https://github.com/CrisisUp/backend.git](https://github.com/CrisisUp/backend.git)
cd backend
````
### 2. Configurar o Banco de Dados (PostgreSQL com Docker)
Certifique-se de que o Docker Desktop esta rodando no seu Windows. Abra seu terminal WSL e execute o seguinte comando para iniciar um contêiner PostgreSQL:
````bash

docker run --name pg-mercadoficticio -e POSTGRES_DB=mercadoficticio -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
````
### 3. Configurar o Projeto
Abrir no IntelliJ IDEA:
Abra o IntelliJ IDEA. Na tela de boas-vindas, selecione "Open" e navegue até a pasta backend que você clonou. Em vez de selecionar a pasta, selecione o arquivo pom.xml diretamente. O IntelliJ o importará como um projeto Maven.

Dica: Se o IntelliJ reabrir o último projeto, vá em File -> Settings -> Appearance & Behavior -> System Settings e desmarque "Reopen projects on startup".

Configurar application.properties:
No IntelliJ, abra src/main/resources/application.properties e adicione as seguintes configurações de conexão com o PostgreSQL:

Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/mercadoficticio
spring.datasource.username=admin
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=8080
Compilar o Projeto com Maven:
No terminal WSL, na raiz do projeto backend, execute:
````bash
./mvnw clean compile
````
Isso garantirá que todas as dependências sejam baixadas e o código seja compilado, incluindo a flag -parameters para o compilador.

### 4. Rodar a Aplicação
No IntelliJ IDEA, localize a classe principal BackendApplication.java (em src/main/java/com/mercadoficticio/backend/) e execute-a (botão direito > "Run 'BackendApplication.main()'").

Você deverá ver no console que o Spring Boot inicia na porta 8080.

### 5. 🌐 Endpoints da API
A API estará disponível em http://localhost:8080. Abaixo estão os principais endpoints e seus usos. Use um cliente REST (Postman, Insomnia, curl) para interagir com eles.
Fornecedores (/api/fornecedores)
````JSON
POST /api/fornecedores
Descrição: Cria um novo fornecedor.
Corpo (JSON):
{ "nome": "Nome do Fornecedor", "contato": "contato@email.com" }
Resposta: 201 Created

GET /api/fornecedores
Descrição: Lista todos os fornecedores.
Resposta: 200 OK

GET /api/fornecedores/{id}
Descrição: Busca um fornecedor por ID.
Resposta: 200 OK ou 404 Not Found

PUT /api/fornecedores/{id}
Descrição: Atualiza um fornecedor.
Corpo (JSON): (igual ao POST)
Resposta: 200 OK ou 404 Not Found

DELETE /api/fornecedores/{id}
Descrição: Deleta um fornecedor.
Resposta: 204 No Content ou 404 Not Found

Produtos (/api/produtos)
POST /api/produtos
Descrição: Cria um novo produto.
Corpo (JSON):
JSON
{
  "nome": "Nome do Produto",
  "descricao": "Descrição do produto",
  "precoCusto": 10.50,
  "precoVenda": 15.99,
  "quantidadeEstoque": 50,
  "fornecedorId": 1
}
Resposta: 201 Created

GET /api/produtos
Descrição: Lista todos os produtos.
Resposta: 200 OK

GET /api/produtos/{id}
Descrição: Busca um produto por ID.
Resposta: 200 OK ou 404 Not Found

PUT /api/produtos/{id}
Descrição: Atualiza um produto.
Corpo (JSON): (igual ao POST)
Resposta: 200 OK ou 404 Not Found

DELETE /api/produtos/{id}
Descrição: Deleta um produto.
Resposta: 204 No Content ou 404 Not Found

Compras (/api/compras)
POST /api/compras
Descrição: Registra uma nova compra de produtos, atualizando o estoque e gerando uma despesa.
Corpo (JSON):
JSON
{
  "fornecedorId": 1,
  "itens": [
    { "produtoId": 1, "quantidade": 100, "precoUnitarioCusto": 5.00 }
  ]
}
Resposta: 201 Created

GET /api/compras
Descrição: Lista todas as compras registradas.
Resposta: 200 OK

GET /api/compras/{id}
Descrição: Busca uma compra por ID.
Resposta: 200 OK ou 404 Not Found

Vendas (/api/vendas)
POST /api/vendas
Descrição: Registra uma nova venda de produtos, diminuindo o estoque e gerando receita.

**Corpo (JSON):1
JSON
{
  "itens": [
    { "produtoId": 1, "quantidade": 5, "precoUnitarioVenda": 12.00 }
  ]
}
Resposta: 201 Created

GET /api/vendas
Descrição: Lista todas as vendas registradas.
Resposta: 200 OK

GET /api/vendas/{id}
Descrição: Busca uma venda por ID.
Resposta: 200 OK ou 404 Not Found

Fluxo de Caixa (/api/fluxo-de-caixa)
POST /api/fluxo-de-caixa/movimentacoes
Descrição: Registra uma nova movimentação financeira avulsa (despesa ou receita).
Corpo (JSON):
JSON
{ "descricao": "Pagamento de Aluguel", "valor": 2000.00, "tipo": "DESPESA", "data": "2025-07-01" }
Resposta: 201 Created

GET /api/fluxo-de-caixa/movimentacoes?startDate={data}&endDate={data}
Descrição: Lista todas as movimentações financeiras dentro de um período.
Exemplo de URL: http://localhost:8080/api/fluxo-de-caixa/movimentacoes?startDate=2025-07-01&endDate=2025-07-31
Resposta: 200 OK

GET /api/fluxo-de-caixa/saldo?startDate={data}&endDate={data}
Descrição: Calcula o saldo líquido (Receitas - Despesas) para um período.
Exemplo de URL: http://localhost:8080/api/fluxo-de-caixa/saldo?startDate=2025-07-01&endDate=2025-07-31
Resposta: 200 OK e um valor numérico.
````
### 6. 🤝 Contribuição
Sinta-se à vontade para explorar, testar e sugerir melhorias!