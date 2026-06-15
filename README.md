# ⚙️ SCGF API - Core Engine

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.14-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

O **SCGF API** é o motor de processamento e persistência do Sistema de Consultas e Gerenciamento de Funcionários. Desenvolvido com uma arquitetura robusta em Spring Boot, ele fornece uma interface RESTful segura e escalável para gerenciar o ciclo de vida dos colaboradores.

---

## 🛠️ Tecnologias Utilizadas

- **Framework:** Spring Boot 3.5.14
- **Linguagem:** Java 21 (LTS)
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** MySQL 8.0
- **Migrações:** Flyway DB
- **Produtividade:** Lombok
- **Validação:** Bean Validation (Jakarta)

---

## ✨ Funcionalidades da API

### ✅ Já Implementado
- **Endpoints de Gestão:** CRUD completo de funcionários com validações de negócio (CPF, idade, e-mail único).
- **Inteligência de Domínio:** Cálculo automático de bônus salarial (+20%) para Desenvolvedores.
- **Consultas Filtradas:**
    - Listagem paginada (Ativos/Inativos).
    - Filtros por Especialidade e Tipo de Conta Bancária.
- **Segurança de Dados:** Validação rigorosa de formatos (CEP, CPF, Conta Bancária).

### 🚀 Roadmap Backend
- [ ] **Segurança Avançada:** Implementação de JWT e Spring Security (RBAC).
- [ ] **Auditoria:** Registro de logs de alteração por usuário.
- [ ] **Relatórios:** Geração de arquivos PDF/Excel para exportação de dados.
- [ ] **Testes:** Expansão da cobertura de testes unitários e de integração.

---

## 📖 Como Utilizar (API)

A API foi projetada para ser consumida por qualquer cliente REST (Web, Mobile ou ferramentas de teste).

> **Interface Web:** Para utilizar a interface visual oficial deste sistema, consulte o repositório [scgf-ui](https://github.com/kauavictorss/scgf-ui).

### Template JSON para Cadastro (POST /funcionarios)

<details>
  <summary>Clique para ver o JSON de exemplo</summary>

```json
{
  "cpf": "000.000.000-00",
  "nome": "Nome do Colaborador",
  "idade": 25,
  "email": "exemplo@email.com",
  "especialidade": "DESENVOLVEDOR",
  "conta": {
    "numConta": "12345678-9",
    "agencia": "0001",
    "tipoConta": "CORRENTE",
    "salario": 5000.00
  },
  "endereco": {
    "cep": "00000-000",
    "logradouro": "Rua Exemplo",
    "bairro": "Bairro",
    "cidade": "Cidade",
    "uf": "UF",	
    "numero": "100",
    "complemento": "Apt 01"
  }
}
```
</details>

---

## 📐 Arquitetura do Projeto

O projeto adota os princípios da **Clean Architecture** (Arquitetura Limpa), promovendo a separação de interesses e garantindo que o núcleo de negócio seja independente de frameworks e ferramentas externas.

### 🏗️ Estrutura de Camadas

```text
scgf-api/
├── application/    # Camada de Orquestração
│   ├── dto/        # Objetos de Transferência de Dados (Records)
│   ├── service/    # Casos de Uso (Lógica de aplicação e coordenação)
│   └── validation/ # Validadores de regras de negócio (Padrão Strategy)
│
├── domain/         # O Coração do Sistema (Independente de Frameworks)
│   ├── model/      # Entidades de negócio com comportamento (Rich Domain Model)
│   └── enums/      # Enums e constantes de domínio
│
└── infrastructure/ # Detalhes Técnicos e Integrações
    ├── controller/ # Endpoints REST (Porta de entrada)
    ├── repository/ # Implementação da persistência (Spring Data JPA)
    ├── config/     # Configurações globais (CORS, Beans)
    └── exception/  # Manipulação global de erros (RestControllerAdvice)
```

### 🔁 Fluxo de Execução
1. O **Controller** recebe a requisição e mapeia para um **DTO**.
2. O **Service** (Application) recebe o DTO e executa as **Validações**.
3. O Service manipula o **Domain Model** para realizar a lógica de negócio.
4. O Service utiliza o **Repository** para persistir as alterações.
5. O resultado é transformado em um DTO de retorno e enviado pelo Controller.

> **Regra de Dependência:** As dependências sempre apontam para dentro. O `Domain` é o núcleo e não conhece as camadas externas (`Application` ou `Infrastructure`).

---

## 🚀 Como Executar o Backend

### Pré-requisitos
- JDK 21+
- Maven 3.x
- MySQL 8.0+

### 1. Clonar o Repositório
```bash
git clone https://github.com/kauavictorss/scgf-api.git
```

### 2. Configurar o Banco de Dados
Ajuste as credenciais no arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scgf_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3. Executar a Aplicação

Você pode usar o Maven instalado na sua máquina ou o **Maven Wrapper** incluso no projeto (recomendado):

```bash
# Usando o Wrapper (Windows)
.\mvnw.cmd spring-boot:run

# Usando o Wrapper (Linux/Mac)
./mvnw spring-boot:run

# Ou Maven global
mvn spring-boot:run
```
A API estará disponível em `http://localhost:8080`.

---

## 👨‍💻 Autor

<div align="center">
  <img src="https://github.com/kauavictorss.png" width="150px" style="border-radius: 50%;" alt="Kauã Victor"/>
  <br>
  <h1>Kauã Victor Silva dos Santos</h1>

[![GitHub](https://img.shields.io/badge/-GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/kauavictorss)
[![LinkedIn](https://img.shields.io/badge/-LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/kaua-victor-santos/)
</div>
