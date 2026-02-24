# Rocketseat Course - Spring Boot

Projeto desenvolvido no curso da Rocketseat sobre Spring Boot, implementando uma aplicação de gerenciamento de tarefas (To-Do List) com Clean Architecture.

## 📊 Status do Projeto

| Funcionalidade | Status | Descrição |
|---------------|--------|-----------|
| 🏗️ Clean Architecture | ✅ Completo | 4 camadas bem definidas |
| 👤 Criar Usuário | ✅ Completo | POST /users com validações |
| 👥 Listar Usuários | ✅ Completo | GET /users |
| 🔍 Buscar Usuário por ID | ✅ Completo | GET /users/{id} |
| 📧 Buscar Usuário por Email | ✅ Completo | GET /users/email/{email} |
| 🔎 Buscar Usuário por Nome | ✅ Completo | GET /users/searchbyname/{name} |
| ✏️ Atualizar Usuário | ✅ Completo | PUT /users/{id} |
| 🗑️ Deletar Usuário | ✅ Completo | DELETE /users/{id} |
| 📝 CRUD de Tasks | ✅ Completo | POST, GET, PUT, DELETE + filtros + start/end |
| 🔐 Criptografia BCrypt | ✅ Completo | Senhas protegidas |
| ✔️ Validações | ✅ Completo | Bean Validation nos DTOs |
| ⚠️ Exception Handling | ✅ Completo | 8 exceções customizadas |
| 🔑 JWT Authentication | ⚠️ Pendente | A ser implementado |
| 🧪 Testes | ⚠️ Pendente | A ser implementado |

## 📚 Documentação

**[📖 Análise Técnica Completa](./ANALISE-TECNICA.md)**

Para uma análise técnica detalhada e completa do projeto, incluindo arquitetura, entidades, DTOs, use cases, fluxos e melhorias sugeridas, consulte o documento:

➡️ **[ANALISE-TECNICA.md](./ANALISE-TECNICA.md)**

### O que você encontrará na análise:

1. ✅ **Estrutura Geral**: Explicação da arquitetura Clean Architecture adotada
2. ✅ **Entidades (Domain)**: Documentação completa de User e Task
3. ✅ **DTOs**: Análise de todos os Data Transfer Objects
4. ✅ **Use Cases**: Detalhamento de todos os casos de uso e seus fluxos
5. ✅ **Repositories**: Interfaces e implementações de persistência
6. ✅ **Controllers**: Endpoints REST e suas responsabilidades
7. ✅ **Segurança**: Implementação de criptografia BCrypt e validações
8. ✅ **Fluxos Completos**: Diagramas passo-a-passo de criação de usuário e tarefa
9. ✅ **Pontos Fortes e Melhorias**: Análise arquitetural e 20 sugestões de melhoria

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.5.10**
- **Spring Data JPA**
- **Spring Security** (BCrypt)
- **MySQL**
- **Lombok**
- **Maven**

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture**:

```
├── domain/          # Entidades e regras de negócio
├── application/     # Casos de uso e DTOs
├── infra/           # Persistência e adaptadores
└── presentation/    # Controllers REST
```

## 📦 Como Executar

### Pré-requisitos

- Java 21+
- MySQL 8.0+
- Maven 3.6+

### Configuração do Banco de Dados

1. Crie o banco de dados MySQL:
```sql
CREATE DATABASE rocketseat_course;
```

2. Configure as credenciais em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rocketseat_course?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=sua_senha
```

### Executar a Aplicação

```bash
# Compilar o projeto
./mvnw clean install

# Executar a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 🧪 Testando a API

### Criar Usuário (POST /users)

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@example.com",
    "password": "senha123"
  }'
```

**Resposta de Sucesso (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "João Silva",
  "email": "joao@example.com",
  "createdAt": "2024-01-15T10:30:00"
}
```

**Resposta de Erro (409 Conflict - Email já existe):**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 409,
  "message": "Email already exists"
}
```

### Listar Todos os Usuários (GET /users)

```bash
curl -X GET http://localhost:8080/users
```

**Resposta de Sucesso (200 OK):**
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "João Silva",
    "email": "joao@example.com",
    "createdAt": "2024-01-15T10:30:00"
  },
  {
    "id": "660e8400-e29b-41d4-a716-446655440001",
    "name": "Maria Santos",
    "email": "maria@example.com",
    "createdAt": "2024-01-15T11:00:00"
  }
]
```

**Resposta de Erro (404 Not Found - Nenhum usuário encontrado):**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "message": "No users found!"
}
```

### Validações e Erros

O sistema possui validação robusta e retorna erros estruturados:

#### Erros de Validação (400 Bad Request)
```bash
# Exemplo: Tentar criar usuário sem email válido
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "email-invalido",
    "password": "senha123"
  }'
```

**Resposta:**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "message": "must be a well-formed email address"
}
```

#### Códigos de Erro Implementados

| Código | Exceção | Descrição |
|--------|---------|-----------|
| 400 | InvalidEmailException | Formato de email inválido |
| 400 | UserCantBeNullException | Usuário não pode ser nulo |
| 400 | TaskCantBeNullException | Tarefa não pode ser nula |
| 400 | MethodArgumentNotValidException | Erro de validação nos campos |
| 404 | UserNotFoundException | Usuário não encontrado |
| 404 | TaskNotFoundException | Tarefa não encontrada |
| 409 | EmailAlreadyExistsException | Email já cadastrado |
| 500 | Exception | Erro interno do servidor |

## 📝 Endpoints Disponíveis

### Users

#### ✅ Implementados
- **POST /users** - Criar novo usuário
  ```json
  {
    "name": "João Silva",
    "email": "joao@example.com",
    "password": "senha123"
  }
  ```
  
- **GET /users** - Listar todos os usuários
  ```
  GET http://localhost:8080/users
  ```

- **GET /users/{id}** - Buscar usuário por ID
  ```
  GET http://localhost:8080/users/{id}
  ```

- **GET /users/email/{email}** - Buscar usuário por email
  ```
  GET http://localhost:8080/users/email/{email}
  ```

- **GET /users/searchbyname/{name}** - Buscar usuários por nome
  ```
  GET http://localhost:8080/users/searchbyname/{name}
  ```

- **PUT /users/{id}** - Atualizar usuário
  ```json
  {
    "name": "João Silva Atualizado",
    "email": "joao.novo@example.com",
    "password": "novaSenha123"
  }
  ```

- **DELETE /users/{id}** - Deletar usuário
  ```
  DELETE http://localhost:8080/users/{id}
  ```

### Tasks

#### ✅ Implementados
- **POST /tasks** - Criar nova tarefa
  ```json
  {
    "title": "Minha tarefa",
    "description": "Descrição da tarefa",
    "startAt": "2024-01-15T10:00:00",
    "endAt": "2024-01-15T18:00:00",
    "priority": "HIGH",
    "userId": "550e8400-e29b-41d4-a716-446655440000"
  }
  ```

- **GET /tasks** - Listar todas as tarefas
- **GET /tasks/{id}** - Buscar tarefa por ID
- **GET /tasks/searchbytitle/{title}** - Buscar tarefas por título
- **GET /tasks/searchbyuser/{user_id}** - Buscar tarefas por usuário
- **GET /tasks/searchbypriority/{priority}** - Buscar tarefas por prioridade
- **PUT /tasks/{id}** - Atualizar tarefa
- **DELETE /tasks/{id}** - Deletar tarefa
- **PUT /tasks/starttask/{id}** - Iniciar tarefa (define startAt com timestamp atual)
- **PUT /tasks/endtask/{id}** - Finalizar tarefa (define endAt com timestamp atual)

### Estrutura de Dados

#### User
```json
{
  "id": "UUID",
  "name": "string (max 100 chars)",
  "email": "string (unique, valid email)",
  "password": "string (criptografado com BCrypt)",
  "createdAt": "datetime (auto-generated)"
}
```

#### Task (Estrutura implementada)
```json
{
  "id": "UUID",
  "title": "string (max 50 chars, required)",
  "description": "string (max 255 chars, required)",
  "startAt": "datetime (optional)",
  "endAt": "datetime (optional)",
  "priority": "string (optional)",
  "user_id": "UUID (foreign key)",
  "createdAt": "datetime (auto-generated)"
}
```

## 📖 Aprendizados e Funcionalidades Implementadas

Este projeto demonstra a implementação de:

### ✅ Totalmente Implementado
- **Clean Architecture com Spring Boot** - Separação clara em 4 camadas
- **Separação em camadas** (Domain, Application, Infra, Presentation)
- **Padrão Repository** - Interfaces no domínio, implementações na infra
- **Use Cases Completos de Task** - Todos os 5 use cases implementados:
  - CreateTaskUseCase
  - FindAllTasksUseCase
  - FindTaskByIdUseCase
  - UpdateTaskUseCase
  - DeleteTaskUseCase
- **Use Cases Completos de User** - Todos os 5 use cases implementados:
  - CreateUserUseCase
  - FindAllUsersUseCase
  - FindUserByIdUseCase
  - UpdateUserUseCase
  - DeleteUserUseCase
- **DTOs e Mappers** - Conversão entre entidades e DTOs
- **Criptografia de senhas** - BCrypt para hash de senhas
- **Validações** - Bean Validation nos DTOs
- **Spring Data JPA** - Persistência com MySQL
- **Exception Handling** - GlobalExceptionHandler com 8 exceções customizadas
- **Dependency Injection** - Injeção por construtor em todas as classes
- **CRUD Completo de Users** - Todos os 7 endpoints REST funcionais
- **CRUD Completo de Tasks** - 10 endpoints REST funcionais:
  - POST /tasks, GET /tasks, GET /tasks/{id}
  - GET /tasks/searchbytitle/{title}, GET /tasks/searchbyuser/{user_id}
  - GET /tasks/searchbypriority/{priority}
  - PUT /tasks/{id}, DELETE /tasks/{id}
  - PUT /tasks/starttask/{id}, PUT /tasks/endtask/{id}

### ❌ Não Implementado
- Autenticação com JWT
- Autorização baseada em roles
- Testes unitários e de integração

## 🔒 Segurança

- ✅ Senhas criptografadas com **BCrypt** (via PasswordEncoder)
- ✅ Validações de entrada nos DTOs (Bean Validation)
- ✅ Unicidade de e-mail garantida no banco de dados
- ✅ Verificação de duplicatas antes de criar usuário
- ✅ CSRF desabilitado (adequado para APIs stateless)
- ⚠️ Autenticação JWT em desenvolvimento
- ⚠️ Autorização baseada em roles em desenvolvimento

## 📈 Status do Projeto

**Versão**: 0.0.1-SNAPSHOT

**Status de Implementação**:
- ✅ Arquitetura Clean Architecture definida e estruturada
- ✅ Camadas bem separadas (Domain, Application, Infra, Presentation)
- ✅ Entidades User e Task modeladas
- ✅ Sistema de exceções customizadas (8 exceções)
- ✅ GlobalExceptionHandler implementado
- ✅ Configuração de segurança com BCrypt
- ✅ **CRUD de User COMPLETO (7/7 endpoints)**:
  - ✅ POST /users - Criar usuário
  - ✅ GET /users - Listar todos
  - ✅ GET /users/{id} - Buscar por ID
  - ✅ GET /users/email/{email} - Buscar por email
  - ✅ GET /users/searchbyname/{name} - Buscar por nome
  - ✅ PUT /users/{id} - Atualizar usuário
  - ✅ DELETE /users/{id} - Deletar usuário
- ✅ Todos os 5 Use Cases de User implementados com lógica completa
- ✅ **CRUD de Task COMPLETO (10 endpoints)**:
  - ✅ POST /tasks - Criar tarefa
  - ✅ GET /tasks - Listar todas
  - ✅ GET /tasks/{id} - Buscar por ID
  - ✅ GET /tasks/searchbytitle/{title} - Buscar por título
  - ✅ GET /tasks/searchbyuser/{user_id} - Buscar por usuário
  - ✅ GET /tasks/searchbypriority/{priority} - Buscar por prioridade
  - ✅ PUT /tasks/{id} - Atualizar tarefa
  - ✅ DELETE /tasks/{id} - Deletar tarefa
  - ✅ PUT /tasks/starttask/{id} - Iniciar tarefa
  - ✅ PUT /tasks/endtask/{id} - Finalizar tarefa
- ✅ Todos os 5 Use Cases de Task implementados com lógica completa
- ❌ Autenticação JWT pendente
- ❌ Testes unitários pendentes

**Próximos Passos**:
1. Adicionar autenticação JWT
2. Adicionar autorização baseada em roles
3. Adicionar testes unitários e de integração
4. Implementar paginação nos endpoints de listagem

Para uma visão completa do status, melhorias sugeridas e análise técnica, consulte [ANALISE-TECNICA.md](./ANALISE-TECNICA.md).

## 🌐 Front End

A interface web para esta API está disponível em um repositório separado:

👉 **[ToDo-List-Rocketseat-Front](https://github.com/jonathan7gb/ToDo-List-Rocketseat-Front)**

Desenvolvido com HTML5, JavaScript e Tailwind CSS, o front end consome diretamente esta API REST.

## 👨‍💻 Autor

Projeto desenvolvido durante o curso Rocketseat - Spring Boot

## 📄 Licença

Este projeto é para fins educacionais.
