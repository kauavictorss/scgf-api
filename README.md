# 🚀 SCGF - Sistema de Consultas e Gerenciamento de Funcionários

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.14-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Vue.js](https://img.shields.io/badge/Vue.js-3.x-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Vite](https://img.shields.io/badge/Vite-Latest-646CFF?style=for-the-badge&logo=vite&logoColor=white)

O **SCGF** é uma solução completa para a gestão de capital humano, focada na eficiência e precisão dos dados. O projeto integra um robusto ecossistema Backend em Spring Boot com uma interface moderna e intuitiva em Vue.js, permitindo o controle total sobre o ciclo de vida dos colaboradores na organização.

---

## 🛠️ Tecnologias Utilizadas

### **Backend (Core API)**
- **Framework:** Spring Boot 3.5.14
- **Linguagem:** Java 21 (LTS)
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** MySQL
- **Migrações:** Flyway DB
- **Produtividade:** Lombok
- **Validação:** Bean Validation (Jakarta)

### **Frontend (UI Experience)** ([Repositório](https://github.com/kauavictorss/scgf-ui))
- **Framework:** Vue 3
- **Build Tool:** Vite
- **UI Components:** PrimeVue & BootstrapVueNext
- **Estilização:** CSS Moderno / Bootstrap 5
- **Comunicação:** Axios

---

## ✨ Funcionalidades Principais

### ✅ Já Implementado
- **Gestão de Colaboradores:** Cadastro completo com validação rigorosa (CPF único, e-mail único, idade mínima 18 anos).
- **Inteligência de Negócio:** Ajuste salarial automático (+20%) para perfis técnicos (Desenvolvedores).
- **Consultas Avançadas:**
    - Listagem paginada de funcionários ativos e inativos.
    - Filtros por Especialidade e Tipo de Conta.
    - Busca detalhada por CPF e E-mail.
- **Ciclo de Vida:** Atualização dinâmica de dados e exclusão lógica (inativação).

### 🚀 Roadmap (Em breve)
- [ ] **Gestão de Cargos:** Controle de faixas salariais e promoções.
- [ ] **Controle de Ponto:** Registro de horas, faltas e gestão de férias.
- [ ] **Dashboard de Relatórios:** Visão analítica por especialidade e status.
- [ ] **Segurança Avançada:** Autenticação JWT e controle de acesso por perfis (RBAC).
- [ ] **Auditoria:** Histórico completo de alterações (Logs).

---

## 📖 Exemplos de Uso

O banco de dados pode ser populado de duas maneiras:
1. **Pela Interface Web (UI):** Utilizando os formulários intuitivos no frontend.
2. **Pela API (REST):** Enviando requisições POST para o endpoint `/funcionarios` utilizando ferramentas como **Postman** ou **Insomnia**.

<details>
  <summary>📍 Template JSON para Cadastro (POST /funcionarios)</summary>

```json
{
  "cpf": "00000000000",
  "nome": "Nome do Colaborador",
  "idade": 25,
  "email": "exemplo@email.com",
  "especialidade": "DESENVOLVEDOR",
  "conta": {
    "numConta": "12345-6",
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

## 📐 Arquitetura do Sistema

O projeto segue os princípios da **Clean Architecture** e **S.O.L.I.D**, garantindo manutenibilidade e escalabilidade:

```text
scgf-api/
├── src/main/java/scgf/api/
│   ├── conta/          # Domínio de Contas Bancárias
│   ├── endereco/       # Domínio de Endereços
│   ├── especialidade/  # Domínio de Especialidades/Cargos
│   └── funcionario/    # Domínio Principal (Controller, Service, Model, Repository)
└── src/resources/db/migration  # Scripts de evolução do banco (Flyway)
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- JDK 21+
- Maven 3.x
- MySQL 8.0+
- Node.js (para o frontend)

### 1. Clonar os Repositórios
```bash
# Backend
git clone https://github.com/kauavictorss/scgf-api.git

# Frontend
git clone https://github.com/kauavictorss/scgf-ui.git
```

### 2. Configurar o Banco de Dados
No arquivo `src/main/resources/application.properties` do backend, ajuste as credenciais do seu MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scgf_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3. Rodar o Backend
```bash
cd scgf-api
mvn spring-boot:run
```

### 4. Rodar o Frontend
```bash
cd scgf-ui
npm install
npm run dev
```

---

## 📄 Licença

Este projeto está sob a licença [MIT](LICENSE).

---

Desenvolvido com ❤️ por [Kauã Victor](https://github.com/kauavictorss)
