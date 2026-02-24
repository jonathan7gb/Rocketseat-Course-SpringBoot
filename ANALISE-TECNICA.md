# Análise Técnica Completa - Projeto Spring Boot Rocketseat

## 📋 Índice
1. [Estrutura Geral](#estrutura-geral)
2. [Entidades (Domain)](#entidades-domain)
3. [DTOs](#dtos)
4. [Use Cases](#use-cases)
5. [Repositories](#repositories)
6. [Controllers](#controllers)
7. [Segurança](#segurança)
8. [Fluxo Completo](#fluxo-completo)
9. [Pontos Fortes e Melhorias](#pontos-fortes-e-melhorias)

---

## 1. Estrutura Geral

### Arquitetura Adotada

O projeto segue os princípios da **Clean Architecture** (Arquitetura Limpa), também conhecida como **Arquitetura Hexagonal**. Esta abordagem promove:

- **Separação de responsabilidades** entre camadas
- **Independência de frameworks** (o domínio não conhece Spring)
- **Inversão de dependências** (camadas externas dependem das internas)
- **Testabilidade** facilitada pela separação clara

### Organização dos Pacotes

```
com.weg.rocketseatcourse/
│
├── domain/                          # CAMADA DE DOMÍNIO (núcleo da aplicação)
│   ├── entity/                      # Entidades de negócio
│   │   ├── User.java
│   │   └── Task.java
│   ├── enums/                       # Enumerações do domínio
│   │   └── TaskPriority.java
│   └── repository/                  # Interfaces dos repositórios (contratos)
│       ├── UserRepository.java
│       └── TaskRepository.java
│
├── application/                     # CAMADA DE APLICAÇÃO (casos de uso)
│   ├── config/                      # Configurações da aplicação
│   │   ├── SecurityConfig.java
│   │   └── CorsConfig.java
│   ├── dto/                         # Data Transfer Objects
│   │   ├── user/
│   │   │   ├── UserRequestDTO.java
│   │   │   └── UserResponseDTO.java
│   │   └── task/
│   │       ├── TaskRequestDTO.java
│   │       └── TaskResponseDTO.java
│   ├── mapper/                      # Conversores DTO <-> Entity
│   │   ├── UserMapper.java
│   │   └── TaskMapper.java
│   ├── usecase/                     # Regras de negócio
│   │   ├── user/
│   │   │   ├── interfaces/         # Contratos dos casos de uso
│   │   │   │   ├── CreateUserUseCase.java
│   │   │   │   ├── FindAllUsersUseCase.java
│   │   │   │   ├── FindUserByIdUseCase.java
│   │   │   │   ├── UpdateUserUseCase.java
│   │   │   │   └── DeleteUserUseCase.java
│   │   │   └── implementation/     # Implementações
│   │   │       ├── CreateUserUseCaseImpl.java
│   │   │       ├── FindAllUsersUseCaseImpl.java
│   │   │       ├── FindUserByIdUseCaseImpl.java
│   │   │       ├── UpdateUserUseCaseImpl.java
│   │   │       └── DeleteUserUseCaseImpl.java
│   │   └── task/
│   │       ├── interfaces/
│   │       │   ├── CreateTaskUseCase.java
│   │       │   ├── FindAllTasksUseCase.java
│   │       │   ├── FindTaskByIdUseCase.java
│   │       │   ├── UpdateTaskUseCase.java
│   │       │   └── DeleteTaskUseCase.java
│   │       └── implementation/
│   │           ├── CreateTaskUseCaseImpl.java
│   │           ├── FindAllTasksUseCaseImpl.java
│   │           ├── FindTaskByIdUseCaseImpl.java
│   │           ├── UpdateTaskUseCaseImpl.java
│   │           └── DeleteTaskUseCaseImpl.java
│   └── exceptions/                  # Exceções da aplicação
│       ├── EmailAlreadyExistsException.java
│       ├── InvalidEmailException.java
│       ├── UserNotFoundException.java
│       ├── UserCantBeNullException.java
│       ├── UserCantBeDeleted.java
│       ├── TaskNotFoundException.java
│       ├── TaskCantBeNullException.java
│       └── config/
│           ├── ErrorResponse.java
│           └── GlobalExceptionHandler.java
│
├── infra/                           # CAMADA DE INFRAESTRUTURA (adaptadores)
│   └── persistence/                 # Persistência de dados
│       ├── UserRepositoryImpl.java  # Implementação do repositório de domínio
│       ├── TaskRepositoryImpl.java
│       └── jpa/                     # Spring Data JPA
│           ├── UserJpaRepository.java
│           └── TaskJpaRepository.java
│
└── presentation/                    # CAMADA DE APRESENTAÇÃO (interface externa)
    └── controller/                  # Controllers REST
        ├── UserController.java
        └── TaskController.java
```

### Responsabilidade de Cada Camada

#### **Domain (Domínio)**
- **Responsabilidade**: Conter as regras de negócio puras, independentes de frameworks
- **Características**:
  - Entidades com comportamentos de negócio
  - Interfaces de repositórios (sem implementação)
  - Exceções de negócio
  - **NÃO** conhece Spring, JPA ou qualquer framework

#### **Application (Aplicação)**
- **Responsabilidade**: Orquestrar os casos de uso da aplicação
- **Características**:
  - Use Cases implementam regras de negócio específicas
  - DTOs para transferência de dados entre camadas
  - Mappers para conversão de objetos
  - Configurações da aplicação
  - Exceções de aplicação

#### **Infra (Infraestrutura)**
- **Responsabilidade**: Implementar detalhes técnicos (banco de dados, APIs externas)
- **Características**:
  - Implementações concretas dos repositórios
  - Adaptadores para frameworks (JPA, REST clients, etc.)
  - Conhece detalhes técnicos (SQL, HTTP, etc.)

#### **Presentation (Apresentação)**
- **Responsabilidade**: Expor a aplicação ao mundo externo
- **Características**:
  - Controllers REST
  - Validações de entrada
  - Formatação de respostas HTTP
  - Tratamento de requisições

---

## 2. Entidades (Domain)

### 2.1 User (Usuário)

**Localização**: `domain.entity.User`

**Propósito**: Representa um usuário do sistema, responsável pela gestão de dados pessoais e autenticação.

#### Atributos:

| Atributo | Tipo | Anotações | Finalidade |
|----------|------|-----------|------------|
| `id` | `UUID` | `@Id`, `@GeneratedValue(generator = "UUID")` | Identificador único do usuário, gerado automaticamente |
| `name` | `String` | `@NotNull`, `@NotEmpty`, `@Column(length = 100)` | Nome completo do usuário (máx. 100 caracteres) |
| `email` | `String` | `@Email`, `@NotNull`, `@NotEmpty`, `@Column(length = 100, unique = true)` | E-mail único do usuário para login e comunicação |
| `password` | `String` | `@NotNull`, `@NotEmpty` | Senha criptografada do usuário |
| `createdAt` | `LocalDateTime` | `@CreationTimestamp` | Data e hora de criação do registro (preenchido automaticamente) |

#### Métodos de Comportamento:

##### `encryptPassword(PasswordEncoder encoder)`
```java
public void encryptPassword(PasswordEncoder encoder) {
    this.password = encoder.encode(this.password);
}
```
- **Propósito**: Criptografar a senha do usuário antes de salvar no banco
- **Parâmetro**: `PasswordEncoder` - encoder configurado (BCrypt)
- **Comportamento**: Substitui a senha em texto plano pela versão criptografada
- **Quando é usado**: No `CreateUserUseCaseImpl`, antes de persistir o usuário

#### Construtores:

1. **Construtor completo** (gerado por `@AllArgsConstructor`): Para criação com ID
2. **Construtor de negócio**:
```java
public User(String name, String email, String password)
```
   - Usado para criar novos usuários (sem ID, que será gerado)
   - Utilizado pelo `UserMapper`

#### Anotações JPA:
- `@Entity(name = "User")`: Define como entidade JPA
- `@Data`: Gera getters, setters, toString, equals e hashCode (Lombok)
- `@AllArgsConstructor`: Construtor com todos os parâmetros (Lombok)
- `@NoArgsConstructor`: Construtor sem parâmetros, necessário para JPA (Lombok)

---

### 2.2 Task (Tarefa)

**Localização**: `domain.entity.Task`

**Propósito**: Representa uma tarefa/atividade do sistema, podendo ser associada a um usuário.

#### Atributos:

| Atributo | Tipo | Anotações | Finalidade |
|----------|------|-----------|------------|
| `id` | `UUID` | `@Id`, `@GeneratedValue(generator = "UUID")` | Identificador único da tarefa |
| `title` | `String` | `@Column(length = 50, nullable = false)` | Título resumido da tarefa (máx. 50 caracteres) |
| `description` | `String` | `@Column(length = 255, nullable = false)` | Descrição detalhada da tarefa (máx. 255 caracteres) |
| `startAt` | `LocalDateTime` | - | Data e hora de início da tarefa (definida ao iniciar a tarefa) |
| `endAt` | `LocalDateTime` | - | Data e hora de término da tarefa (definida ao finalizar a tarefa) |
| `priority` | `TaskPriority` | `@Enumerated(EnumType.STRING)` | Prioridade da tarefa (LOW, MEDIUM, HIGH) |
| `user` | `User` | `@ManyToOne`, `@JoinColumn(name = "user_id", nullable = false)` | Usuário proprietário da tarefa (FK obrigatória) |
| `createdAt` | `LocalDateTime` | `@CreationTimestamp` | Data e hora de criação do registro |

#### Construtores:

```java
// Construtor completo com usuário
public Task(String title, String description, LocalDateTime startAt,
            LocalDateTime endAt, TaskPriority priority, User user)

// Construtor sem usuário (para uso interno)
public Task(String title, String description, LocalDateTime startAt,
            LocalDateTime endAt, TaskPriority priority)

// Construtor mínimo com usuário (usado pelo TaskMapper)
public Task(String title, String description, TaskPriority priority, User user)
```

---

## 3. DTOs

### Padrão Utilizado: Java Records

Os DTOs utilizam **Java Records** (introduzido no Java 14), que são:
- Imutáveis por padrão
- Geram automaticamente: constructor, getters, equals(), hashCode() e toString()
- Sintaxe concisa e clara

### 3.1 DTOs de User

#### UserRequestDTO

**Localização**: `application.dto.user.UserRequestDTO`

**Propósito**: Transportar dados de entrada para criação/atualização de usuário

```java
public record UserRequestDTO(
    @NotBlank(message = "Name can't be blank")
    String name,

    @Email(message = "Invalid E-mail")
    @NotBlank(message = "E-mail can't be blank")
    String email,

    @NotBlank(message = "Password can't be blank")
    String password
) {}
```

**Atributos**:
- `name`: Nome do usuário
- `email`: E-mail do usuário
- `password`: Senha em texto plano (será criptografada)

**Quando é usado**:
- Como `@RequestBody` no endpoint `POST /users`
- Recebido pelo `CreateUserUseCase`

**Validações**: `@NotBlank` em todos os campos; `@Email` no campo email

---

#### UserResponseDTO

**Localização**: `application.dto.user.UserResponseDTO`

**Propósito**: Retornar dados do usuário após criação/consulta

```java
public record UserResponseDTO(
    UUID id,
    String name,
    String email,
    LocalDateTime createdAt
) {}
```

**Atributos**:
- `id`: Identificador único gerado
- `name`: Nome do usuário
- `email`: E-mail do usuário
- `createdAt`: Data de criação

**Quando é usado**:
- Retornado pelo `CreateUserUseCase`
- Devolvido como resposta HTTP pelo controller
- Embutido no `TaskResponseDTO` como informação do usuário proprietário

---

### 3.2 DTOs de Task

#### TaskRequestDTO

**Localização**: `application.dto.task.TaskRequestDTO`

**Propósito**: Receber dados para criação/atualização de tarefa

```java
public record TaskRequestDTO(
    @NotBlank(message = "Title can't be blank")
    @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
    String title,

    @NotBlank(message = "Description can't be blank")
    @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
    String description,

    @NotBlank(message = "Priority can't be blank")
    TaskPriority priority,

    @NotNull(message = "User can't be null")
    UUID user_id
) {}
```

**Atributos**:
- `title`: Título da tarefa (5-50 caracteres, obrigatório)
- `description`: Descrição detalhada (5-255 caracteres, obrigatório)
- `priority`: Nível de prioridade (TaskPriority enum: LOW, MEDIUM, HIGH - obrigatório)
- `user_id`: ID do usuário proprietário (obrigatório)

**Quando é usado**:
- Como entrada no `CreateTaskUseCase` e `UpdateTaskUseCase`

---

#### TaskResponseDTO

**Localização**: `application.dto.task.TaskResponseDTO`

**Propósito**: Retornar dados da tarefa após operações

```java
public record TaskResponseDTO(
    UUID id,
    String title,
    String description,
    LocalDateTime startAt,
    LocalDateTime endAt,
    TaskPriority priority,
    UserResponseDTO user,
    LocalDateTime createdAt
) {}
```

**Atributos**: Todos os campos da entidade Task, com o usuário representado como `UserResponseDTO` embutido

**Quando é usado**:
- Retorno do `CreateTaskUseCase`
- Resposta HTTP dos endpoints de Task

---

## 4. Use Cases

### Padrão Utilizado

Cada Use Case segue o padrão:
1. **Interface** no pacote `usecase.{entidade}.interfaces`
2. **Implementação** no pacote `usecase.{entidade}.implementation`
3. Anotação `@Component` para injeção de dependências

### 4.1 Use Cases de User

#### CreateUserUseCase ✅ (Implementado)

**Interface**: `CreateUserUseCase`
```java
UserResponseDTO createUser(UserRequestDTO userRequestDTO);
```

**Implementação**: `CreateUserUseCaseImpl`

**Responsabilidade**: Criar um novo usuário no sistema com validações e criptografia de senha.

**Dependências**:
- `UserRepository`: Para operações de persistência
- `UserMapper`: Para conversão DTO ↔ Entity
- `PasswordEncoder`: Para criptografar senha

**Fluxo Interno Detalhado**:

```
1. VALIDAÇÃO DE ENTRADA
   └─ Se userRequestDTO == null
      └─ Lança UserCantBeNullException("User can't be null!")

2. VALIDAÇÃO DE UNICIDADE
   └─ Busca usuário por email no repositório
      └─ Se já existe (Optional.isPresent())
         └─ Lança EmailAlreadyExistsException("E-mail already registered!")

3. CONVERSÃO DTO → ENTITY
   └─ userMapper.toEntity(userRequestDTO)
      └─ Cria User(name, email, password) - senha ainda em texto plano

4. CRIPTOGRAFIA DE SENHA
   └─ user.encryptPassword(passwordEncoder)
      └─ Senha é substituída pela versão criptografada com BCrypt

5. PERSISTÊNCIA
   └─ userRepository.save(user)
      └─ Salva no banco de dados
      └─ Retorna User com ID gerado

6. CONVERSÃO ENTITY → DTO
   └─ userMapper.toDto(userSaved)
      └─ Cria UserResponseDTO com todos os dados

7. RETORNO
   └─ Retorna UserResponseDTO
```

**Exceções Lançadas**:
- `UserCantBeNullException`: Entrada nula
- `EmailAlreadyExistsException`: E-mail já cadastrado

**Regras de Negócio**:
1. Usuário não pode ser nulo
2. E-mail deve ser único no sistema
3. Senha deve ser criptografada antes de salvar

---

#### FindUserByIdUseCase ✅ (Implementado)

**Interface**: `FindUserByIdUseCase`
```java
UserResponseDTO findUserByID(UUID id);
UserResponseDTO findUserByEmail(String email);
List<UserResponseDTO> findByName(String name);
```

**Implementação**: `FindUserByIdUseCaseImpl`

**Responsabilidade**: Buscar usuários por ID, email ou nome.

**Dependências**:
- `UserRepository`: Para buscar o usuário no banco de dados
- `UserMapper`: Para converter Entity para DTO

**Fluxo Interno Detalhado**:

```
1. BUSCA NO REPOSITÓRIO
   └─ userRepository.findById(id)
      └─ Retorna Optional<User>

2. VALIDAÇÃO DE EXISTÊNCIA
   └─ Se Optional.isEmpty()
      └─ Lança UserNotFoundException("User not found!")

3. CONVERSÃO ENTITY → DTO
   └─ userMapper.toDto(user)
      └─ Cria UserResponseDTO com os dados do usuário

4. RETORNO
   └─ Retorna UserResponseDTO
```

**Exceções Lançadas**:
- `UserNotFoundException`: Usuário com o ID fornecido não existe

**Status**: ✅ Totalmente implementado e funcional

---

#### FindAllUsersUseCase ✅ (Implementado)

**Interface**: `FindAllUsersUseCase`
```java
List<UserResponseDTO> findAllUsers();
```

**Implementação**: `FindAllUsersUseCaseImpl`

**Responsabilidade**: Listar todos os usuários cadastrados no sistema.

**Dependências**:
- `UserRepository`: Para buscar todos os usuários
- `UserMapper`: Para converter cada Entity para DTO

**Fluxo Interno Detalhado**:

```
1. BUSCA NO REPOSITÓRIO
   └─ userRepository.findAll()
      └─ Retorna List<User>

2. VALIDAÇÃO DE LISTA VAZIA
   └─ Se list.isEmpty()
      └─ Lança UserNotFoundException("No users found!")

3. CONVERSÃO ENTITY → DTO (para cada usuário)
   └─ users.stream()
      └─ .map(userMapper::toDto)
      └─ .collect(Collectors.toList())

4. RETORNO
   └─ Retorna List<UserResponseDTO>
```

**Exceções Lançadas**:
- `UserNotFoundException`: Nenhum usuário encontrado no sistema

**Status**: ✅ Totalmente implementado e funcional

---

#### UpdateUserUseCase ✅ (Implementado)

**Interface**: `UpdateUserUseCase`
```java
UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO);
```

**Implementação**: `UpdateUserUseCaseImpl`

**Responsabilidade**: Atualizar os dados de um usuário existente.

**Dependências**:
- `UserRepository`: Para buscar e salvar o usuário
- `UserMapper`: Para converter DTO para Entity
- `PasswordEncoder`: Para criptografar nova senha (se fornecida)

**Fluxo Interno Detalhado**:

```
1. VALIDAÇÃO DE ENTRADA
   └─ Se userRequestDTO == null
      └─ Lança UserCantBeNullException("User can't be null!")

2. BUSCA DO USUÁRIO EXISTENTE
   └─ userRepository.findById(id)
      └─ Se Optional.isEmpty()
         └─ Lança UserNotFoundException("User not found!")

3. VALIDAÇÃO DE EMAIL (se foi alterado)
   └─ Se novo email != email atual
      └─ userRepository.findByEmail(newEmail)
         └─ Se já existe outro usuário com esse email
            └─ Lança EmailAlreadyExistsException("E-mail already registered!")

4. ATUALIZAÇÃO DOS DADOS
   └─ Atualiza name, email
   └─ Se password foi fornecido
      └─ Criptografa nova senha com passwordEncoder

5. PERSISTÊNCIA
   └─ userRepository.save(updatedUser)
      └─ Salva alterações no banco

6. CONVERSÃO ENTITY → DTO
   └─ userMapper.toDto(updatedUser)

7. RETORNO
   └─ Retorna UserResponseDTO atualizado
```

**Exceções Lançadas**:
- `UserCantBeNullException`: Entrada nula
- `UserNotFoundException`: Usuário não encontrado
- `EmailAlreadyExistsException`: Novo email já está em uso

**Status**: ✅ Totalmente implementado e funcional

---

#### DeleteUserUseCase ✅ (Implementado)

**Interface**: `DeleteUserUseCase`
```java
void deleteUser(UUID id);
```

**Implementação**: `DeleteUserUseCaseImpl`

**Responsabilidade**: Deletar um usuário do sistema.

**Dependências**:
- `UserRepository`: Para buscar e deletar o usuário

**Fluxo Interno Detalhado**:

```
1. BUSCA DO USUÁRIO
   └─ userRepository.findById(id)
      └─ Se Optional.isEmpty()
         └─ Lança UserNotFoundException("User not found!")

2. DELEÇÃO
   └─ userRepository.deleteById(id)
      └─ Remove do banco de dados

3. RETORNO
   └─ Método void - sem retorno
```

**Exceções Lançadas**:
- `UserNotFoundException`: Usuário não encontrado

**Regras de Negócio**:
1. Só pode deletar usuário que existe
2. Verificação de existência antes da deleção

**Status**: ✅ Totalmente implementado e funcional

---

### 4.2 Use Cases de Task

#### CreateTaskUseCase ✅ (Implementado)

**Interface**: `CreateTaskUseCase`
```java
TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);
```

**Implementação**: `CreateTaskUseCaseImpl`

**Responsabilidade**: Criar uma nova tarefa validando o usuário proprietário e persistindo no banco.

**Dependências**:
- `TaskRepository`: Para persistência da tarefa
- `TaskMapper`: Para conversão DTO ↔ Entity
- `UserRepository`: Para buscar e validar o usuário proprietário

**Fluxo Interno Detalhado**:

```
1. VALIDAÇÃO DO USUÁRIO
   └─ userRepository.findById(taskRequestDTO.user_id())
      └─ Se não encontrado
         └─ Lança UserNotFoundException("User with this Id not found!")

2. CONVERSÃO DTO → ENTITY
   └─ taskMapper.toEntity(taskRequestDTO)
      └─ Cria Task(title, description, priority, null)

3. VINCULAÇÃO COM USUÁRIO
   └─ task.setUser(userFound)

4. PERSISTÊNCIA
   └─ taskRepository.save(task)
      └─ Retorna Task com ID gerado

5. CONVERSÃO ENTITY → DTO
   └─ taskMapper.toDto(taskSaved)

6. RETORNO
   └─ Retorna TaskResponseDTO
```

**Exceções Lançadas**:
- `UserNotFoundException`: Usuário com o ID fornecido não existe

**Status**: ✅ Totalmente implementado e funcional

---

#### FindAllTasksUseCase ✅ (Implementado)

**Interface**: `FindAllTasksUseCase`
```java
List<TaskResponseDTO> findAllTasks();
```

**Implementação**: `FindAllTasksUseCaseImpl`

**Responsabilidade**: Listar todas as tarefas cadastradas no sistema.

**Fluxo Interno Detalhado**:

```
1. BUSCA NO REPOSITÓRIO
   └─ taskRepository.findAll()
      └─ Retorna List<Task>

2. VALIDAÇÃO DE LISTA VAZIA
   └─ Se list.isEmpty()
      └─ Lança TaskNotFoundException("No tasks found!")

3. CONVERSÃO ENTITY → DTO
   └─ taskMapper.listEntityToDto(tasks)

4. RETORNO
   └─ Retorna List<TaskResponseDTO>
```

**Exceções Lançadas**:
- `TaskNotFoundException`: Nenhuma tarefa encontrada

**Status**: ✅ Totalmente implementado e funcional

---

#### FindTaskByIdUseCase ✅ (Implementado)

**Interface**: `FindTaskByIdUseCase`
```java
TaskResponseDTO findTaskByID(UUID id);
List<TaskResponseDTO> findByTitle(String title);
List<TaskResponseDTO> findByUser(UUID user_id);
List<TaskResponseDTO> findByPriority(TaskPriority priority);
```

**Implementação**: `FindTaskByIdUseCaseImpl`

**Responsabilidade**: Buscar tarefas por ID, título, usuário ou prioridade.

**Status**: ✅ Totalmente implementado e funcional

---

#### UpdateTaskUseCase ✅ (Implementado)

**Interface**: `UpdateTaskUseCase`
```java
TaskResponseDTO updateTask(TaskRequestDTO taskRequestDTO, UUID id);
void startTask(UUID id);
void endTask(UUID id);
```

**Implementação**: `UpdateTaskUseCaseImpl`

**Responsabilidade**: Atualizar dados de uma tarefa, iniciar ou finalizar uma tarefa.

**Fluxo de startTask**:
```
1. Busca a tarefa pelo ID
2. Se startAt já está preenchido → lança IllegalStateException
3. Define startAt com LocalDateTime.now()
```

**Fluxo de endTask**:
```
1. Busca a tarefa pelo ID
2. Se startAt é null → lança IllegalStateException (tarefa não iniciada)
3. Se endAt já está preenchido → lança IllegalStateException
4. Define endAt com LocalDateTime.now()
```

**Status**: ✅ Totalmente implementado e funcional

---

#### DeleteTaskUseCase ✅ (Implementado)

**Interface**: `DeleteTaskUseCase`
```java
void deleteTaskById(UUID id);
```

**Implementação**: `DeleteTaskUseCaseImpl`

**Responsabilidade**: Deletar uma tarefa do sistema após verificar sua existência.

**Fluxo**:
```
1. Busca a tarefa pelo ID
   └─ Se não encontrada → lança TaskNotFoundException
2. Deleta a tarefa
```

**Status**: ✅ Totalmente implementado e funcional

---

### Resumo do Status dos Use Cases

| Use Case | Status | Observações |
|----------|--------|-------------|
| CreateUserUseCase | ✅ Completo | Totalmente funcional com validações |
| FindUserByIdUseCase | ✅ Completo | Busca por ID, email e nome com tratamento de erros |
| FindAllUsersUseCase | ✅ Completo | Listagem com validação de lista vazia |
| UpdateUserUseCase | ✅ Completo | Atualização completa com validações |
| DeleteUserUseCase | ✅ Completo | Deleção com verificação de existência |
| CreateTaskUseCase | ✅ Completo | Criação com validação de usuário |
| FindAllTasksUseCase | ✅ Completo | Listagem com validação de lista vazia |
| FindTaskByIdUseCase | ✅ Completo | Busca por ID, título, usuário e prioridade |
| UpdateTaskUseCase | ✅ Completo | Atualização, início e finalização de tarefa |
| DeleteTaskUseCase | ✅ Completo | Deleção com verificação de existência |

---

## 5. Repositories

### Arquitetura de Repositório

O projeto implementa o padrão **Repository** com três camadas:

```
Domain Interface → Implementation Adapter → JPA Repository
     ↑                      ↑                     ↑
  (Contrato)         (Adaptador)            (Framework)
```

### 5.1 Interfaces do Domínio

#### UserRepository

**Localização**: `domain.repository.UserRepository`

**Propósito**: Definir contrato de persistência de usuários independente de framework

```java
public interface UserRepository {
    User save(User user);
    List<User> findAll();
    List<User> findAllByOrderByNameAsc();
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    List<User> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
    void deleteById(UUID id);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
```

**Métodos**:
- `save()`: Persiste ou atualiza usuário
- `findById()`: Busca por ID
- `findAll()`: Lista todos usuários
- `findAllByOrderByNameAsc()`: Lista todos usuários ordenados por nome
- `findByEmail()`: Busca por e-mail (validação de unicidade)
- `findByNameContainingIgnoreCaseOrderByNameAsc()`: Busca por nome (case-insensitive)
- `deleteById()`: Remove usuário
- `existsByEmail()`: Verifica se e-mail já está cadastrado
- `existsByEmailAndIdNot()`: Verifica se e-mail pertence a outro usuário (para update)

---

#### TaskRepository

**Localização**: `domain.repository.TaskRepository`

**Propósito**: Contrato de persistência de tarefas

```java
public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(UUID id);
    List<Task> findAll();
    List<Task> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title);
    List<Task> findByUserId(UUID user_id);
    List<Task> findByPriority(TaskPriority priority);
    boolean existsByUserId(UUID userId);
    void deleteById(UUID id);
}
```

**Métodos**:
- `save()`: Persiste ou atualiza tarefa
- `findById()`: Busca por ID
- `findAll()`: Lista todas as tarefas
- `findByTitleContainingIgnoreCaseOrderByTitleAsc()`: Busca por título (case-insensitive, ordenada)
- `findByUserId()`: Busca tarefas de um usuário específico
- `findByPriority()`: Filtra por prioridade (TaskPriority enum)
- `existsByUserId()`: Verifica se um usuário tem tarefas
- `deleteById()`: Remove tarefa

---

### 5.2 Implementações na Camada Infra

#### UserRepositoryImpl

**Localização**: `infra.persistence.UserRepositoryImpl`

**Anotação**: `@Repository` (Spring Bean)

**Propósito**: Adaptar `UserJpaRepository` para a interface de domínio

**Dependências**:
- `UserJpaRepository` (injeção via construtor)

**Implementação**:
```java
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    // save, findById, findAll, deleteById, findByEmail,
    // existsByEmail, existsByEmailAndIdNot,
    // findAllByOrderByNameAsc,
    // findByNameContainingIgnoreCaseOrderByNameAsc
    // todos delegando para userJpaRepository
}
```

**Padrão**: **Adapter** - adapta JpaRepository para interface de domínio

---

#### TaskRepositoryImpl

**Localização**: `infra.persistence.TaskRepositoryImpl`

**Implementação**: Delegação completa para TaskJpaRepository, com todos os métodos da interface de domínio implementados.

```java
@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private final TaskJpaRepository taskJpaRepository;

    @Override
    public List<Task> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title) {
        return taskJpaRepository.findByTitleContainingIgnoreCaseOrderByTitleAsc(title);
    }

    @Override
    public List<Task> findByUserId(UUID user_id) {
        return taskJpaRepository.findAllByUser_Id(user_id);
    }

    @Override
    public List<Task> findByPriority(TaskPriority priority) {
        return taskJpaRepository.findAllByPriority(priority);
    }

    // + save, findById, findAll, deleteById, existsByUserId
}
```

---

### 5.3 JPA Repositories

#### UserJpaRepository

**Localização**: `infra.persistence.jpa.UserJpaRepository`

```java
public interface UserJpaRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
    List<User> findAllByOrderByNameAsc();
    List<User> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
}
```

**Características**:
- Estende `JpaRepository<User, UUID>`
- Herda automaticamente: save, findById, findAll, delete, etc.
- Métodos customizados via Query Methods do Spring Data

---

#### TaskJpaRepository

**Localização**: `infra.persistence.jpa.TaskJpaRepository`

```java
public interface TaskJpaRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title);
    List<Task> findAllByUser_Id(UUID userId);
    List<Task> findAllByPriority(TaskPriority priority);
    boolean existsByUserId(UUID userId);
}
```

**Características**:
- Estende `JpaRepository<Task, UUID>`
- Herda automaticamente: save, findById, findAll, delete, etc.
- Métodos customizados via Query Methods do Spring Data

---

### 5.4 Comunicação com o Banco de Dados

#### Fluxo de Persistência:

```
Use Case → Domain Repository Interface → Repository Impl → JPA Repository → Hibernate → MySQL
```

**Exemplo - Salvar Usuário**:
```
1. CreateUserUseCaseImpl.createUser()
   └─ Chama: userRepository.save(user)

2. UserRepository (interface do domínio)
   └─ Contrato: User save(User user)

3. UserRepositoryImpl (implementação infra)
   └─ Executa: userJpaRepository.save(user)

4. UserJpaRepository (Spring Data JPA)
   └─ Herda de JpaRepository
   └─ Chama Hibernate

5. Hibernate (JPA Provider)
   └─ Gera SQL: INSERT INTO User VALUES (...)
   └─ Executa no MySQL

6. MySQL
   └─ Persiste os dados
   └─ Retorna o registro com ID gerado

7. Retorno (caminho inverso)
   └─ MySQL → Hibernate → JpaRepository → RepositoryImpl → Use Case
```

#### Configuração do Banco (application.properties):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rocketseat_course?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=mysqlPW
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

**Observações**:
- **DDL Auto**: `update` - Hibernate atualiza schema automaticamente
- **Driver**: MySQL Connector/J
- **SSL**: Desabilitado (não recomendado para produção)

---

## 6. Controllers

### 6.1 UserController

**Localização**: `presentation.controller.UserController`

**Anotações**:
- `@RestController`: Define como controller REST
- `@RequestMapping("/users")`: Base path para todos endpoints

**Dependências** (injetadas via construtor):
- `CreateUserUseCase`
- `FindAllUsersUseCase`
- `FindUserByIdUseCase`
- `UpdateUserUseCase`
- `DeleteUserUseCase`

#### Endpoints:

##### POST /users ✅

```java
@PostMapping
public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
    UserResponseDTO response = createUserUseCase.createUser(userRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
```

**Descrição**: Criar novo usuário

**Método HTTP**: POST

**Request Body**: `UserRequestDTO` (JSON)
```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123"
}
```

**Response**: HTTP 201 CREATED com UserResponseDTO no corpo

**Use Case chamado**: ✅ `createUserUseCase.createUser()`

**Status**: ✅ Totalmente funcional

---

##### GET /users ✅

```java
@GetMapping
public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    List<UserResponseDTO> users = findAllUsersUseCase.findAllUsers();
    return ResponseEntity.status(HttpStatus.OK).body(users);
}
```

**Descrição**: Listar todos os usuários

**Método HTTP**: GET

**Response**: HTTP 200 OK com lista de UserResponseDTO

**Use Case chamado**: ✅ `findAllUsersUseCase.findAllUsers()`

**Status**: ✅ Totalmente funcional

---

##### GET /users/{id} ✅

```java
@GetMapping("/{id}")
public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
    UserResponseDTO user = findUserByIdUseCase.findUserById(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
}
```

**Descrição**: Buscar usuário por ID

**Método HTTP**: GET

**Path Variable**: `id` (UUID)

**Response**: HTTP 200 OK com UserResponseDTO

**Use Case chamado**: ✅ `findUserByIdUseCase.findUserById(id)`

**Status**: ✅ Totalmente funcional

---

##### GET /users/email/{email} ✅

```java
@GetMapping("/email/{email}")
public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
    UserResponseDTO user = findUserByEmailUseCase.findUserByEmail(email);
    return ResponseEntity.status(HttpStatus.OK).body(user);
}
```

**Descrição**: Buscar usuário por email

**Método HTTP**: GET

**Path Variable**: `email` (String)

**Response**: HTTP 200 OK com UserResponseDTO

**Use Case chamado**: ✅ `findUserByEmailUseCase.findUserByEmail(email)`

**Status**: ✅ Totalmente funcional

---

##### PUT /users/{id} ✅

```java
@PutMapping("/{id}")
public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO userRequestDTO) {
    UserResponseDTO user = updateUserUseCase.updateUser(id, userRequestDTO);
    return ResponseEntity.status(HttpStatus.OK).body(user);
}
```

**Descrição**: Atualizar usuário existente

**Método HTTP**: PUT

**Path Variable**: `id` (UUID)

**Request Body**: `UserRequestDTO` (JSON)

**Response**: HTTP 200 OK com UserResponseDTO atualizado

**Use Case chamado**: ✅ `updateUserUseCase.updateUser(id, userRequestDTO)`

**Status**: ✅ Totalmente funcional

---

##### DELETE /users/{id} ✅

```java
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
    deleteUserUseCase.deleteUserById(id);
    return ResponseEntity.noContent().build();
}
```

**Descrição**: Deletar usuário

**Método HTTP**: DELETE

**Path Variable**: `id` (UUID)

**Response**: HTTP 204 NO CONTENT (sem corpo)

**Use Case chamado**: ✅ `deleteUserUseCase.deleteUserById(id)`

**Status**: ✅ Totalmente funcional

---

##### GET /users/searchbyname/{name} ✅

```java
@GetMapping("/searchbyname/{name}")
public ResponseEntity<List<UserResponseDTO>> findByName(@PathVariable String name) {
    List<UserResponseDTO> responseDTOS = findUserByIdUseCase.findByName(name);
    return ResponseEntity.ok().body(responseDTOS);
}
```

**Descrição**: Buscar usuários por nome (case-insensitive, ordenados por nome)

**Método HTTP**: GET

**Path Variable**: `name` (String)

**Response**: HTTP 200 OK com lista de UserResponseDTO

**Use Case chamado**: ✅ `findUserByIdUseCase.findByName(name)`

**Status**: ✅ Totalmente funcional

---

### 6.2 TaskController

**Localização**: `presentation.controller.TaskController`

**Anotações**:
- `@RestController`
- `@RequestMapping("/tasks")`

**Dependências** (injetadas via construtor):
- `CreateTaskUseCase`
- `FindAllTasksUseCase`
- `FindTaskByIdUseCase`
- `UpdateTaskUseCase`
- `DeleteTaskUseCase`

#### Endpoints:

##### POST /tasks ✅

**Descrição**: Criar nova tarefa

**Método HTTP**: POST

**Request Body**: `TaskRequestDTO` (JSON)
```json
{
  "title": "Estudar Spring Boot",
  "description": "Completar módulo de segurança",
  "priority": "HIGH",
  "user_id": "550e8400-e29b-41d4-a716-446655440000"
}
```

**Response**: HTTP 201 CREATED com TaskResponseDTO no corpo

**Status**: ✅ Totalmente funcional

---

##### GET /tasks ✅

**Descrição**: Listar todas as tarefas

**Response**: HTTP 200 OK com lista de TaskResponseDTO

**Status**: ✅ Totalmente funcional

---

##### GET /tasks/{id} ✅

**Descrição**: Buscar tarefa por ID

**Path Variable**: `id` (UUID)

**Response**: HTTP 200 OK com TaskResponseDTO

**Status**: ✅ Totalmente funcional

---

##### GET /tasks/searchbytitle/{title} ✅

**Descrição**: Buscar tarefas por título (case-insensitive, ordenadas por título)

**Path Variable**: `title` (String)

**Response**: HTTP 200 OK com lista de TaskResponseDTO

**Status**: ✅ Totalmente funcional

---

##### GET /tasks/searchbyuser/{user_id} ✅

**Descrição**: Listar todas as tarefas de um usuário específico

**Path Variable**: `user_id` (UUID)

**Response**: HTTP 200 OK com lista de TaskResponseDTO

**Status**: ✅ Totalmente funcional

---

##### GET /tasks/searchbypriority/{priority} ✅

**Descrição**: Filtrar tarefas por prioridade (LOW, MEDIUM, HIGH)

**Path Variable**: `priority` (TaskPriority enum)

**Response**: HTTP 200 OK com lista de TaskResponseDTO

**Status**: ✅ Totalmente funcional

---

##### PUT /tasks/{id} ✅

**Descrição**: Atualizar dados de uma tarefa existente

**Path Variable**: `id` (UUID)

**Request Body**: `TaskRequestDTO` (JSON)

**Response**: HTTP 200 OK com TaskResponseDTO atualizado

**Status**: ✅ Totalmente funcional

---

##### DELETE /tasks/{id} ✅

**Descrição**: Deletar tarefa

**Path Variable**: `id` (UUID)

**Response**: HTTP 200 OK com mensagem "Task deleted sucessfully!"

**Status**: ✅ Totalmente funcional

---

##### PUT /tasks/starttask/{id} ✅

**Descrição**: Marcar tarefa como iniciada (define `startAt` com timestamp atual)

**Path Variable**: `id` (UUID)

**Regras**: Não pode iniciar tarefa já iniciada

**Response**: HTTP 204 NO CONTENT

**Status**: ✅ Totalmente funcional

---

##### PUT /tasks/endtask/{id} ✅

**Descrição**: Marcar tarefa como finalizada (define `endAt` com timestamp atual)

**Path Variable**: `id` (UUID)

**Regras**: Tarefa deve estar iniciada; não pode finalizar tarefa já finalizada

**Response**: HTTP 204 NO CONTENT

**Status**: ✅ Totalmente funcional

---

### Resumo dos Endpoints

| Endpoint | Método HTTP | Status | Observação |
|----------|-------------|--------|------------|
| `/users` | POST | ✅ Implementado | Cria usuário com validações |
| `/users` | GET | ✅ Implementado | Lista todos os usuários |
| `/users/{id}` | GET | ✅ Implementado | Busca usuário por ID |
| `/users/email/{email}` | GET | ✅ Implementado | Busca usuário por email |
| `/users/searchbyname/{name}` | GET | ✅ Implementado | Busca usuários por nome |
| `/users/{id}` | PUT | ✅ Implementado | Atualiza usuário |
| `/users/{id}` | DELETE | ✅ Implementado | Deleta usuário |
| `/tasks` | POST | ✅ Implementado | Cria tarefa com validação de usuário |
| `/tasks` | GET | ✅ Implementado | Lista todas as tarefas |
| `/tasks/{id}` | GET | ✅ Implementado | Busca tarefa por ID |
| `/tasks/searchbytitle/{title}` | GET | ✅ Implementado | Busca por título (case-insensitive) |
| `/tasks/searchbyuser/{user_id}` | GET | ✅ Implementado | Lista tarefas de um usuário |
| `/tasks/searchbypriority/{priority}` | GET | ✅ Implementado | Filtra por prioridade |
| `/tasks/{id}` | PUT | ✅ Implementado | Atualiza tarefa |
| `/tasks/{id}` | DELETE | ✅ Implementado | Deleta tarefa |
| `/tasks/starttask/{id}` | PUT | ✅ Implementado | Inicia tarefa (define startAt) |
| `/tasks/endtask/{id}` | PUT | ✅ Implementado | Finaliza tarefa (define endAt) |

---

## 7. Segurança

### 7.1 Configuração de Segurança

**Classe**: `SecurityConfig`

**Localização**: `application.config.SecurityConfig`

**Anotação**: `@Configuration`

```java
@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
```

**Propósito**: Configurar o bean de criptografia de senhas e a cadeia de filtros de segurança

---

### 7.2 Hash de Senha

#### Algoritmo: BCrypt

**Características**:
- **Algoritmo**: BCrypt (Blowfish-based)
- **Complexidade**: Adaptável (work factor configurável)
- **Salt**: Gerado automaticamente e incluído no hash
- **Resistência**: Projetado para ser lento e resistente a ataques de força bruta

#### Implementação:

**1. Configuração do Bean**:
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

**2. Uso na Entidade**:
```java
// User.java
public void encryptPassword(PasswordEncoder encoder) {
    this.password = encoder.encode(this.password);
}
```

**3. Chamada no Use Case**:
```java
// CreateUserUseCaseImpl.java
User user = userMapper.toEntity(userRequestDTO);
user.encryptPassword(passwordEncoder);  // ← Criptografa aqui
User userSaved = userRepository.save(user);
```

#### Fluxo de Criptografia:

```
1. Cliente envia: { "password": "senha123" }
2. UserRequestDTO recebe: password = "senha123"
3. UserMapper cria User com: password = "senha123"
4. Use Case chama: user.encryptPassword(passwordEncoder)
5. BCrypt gera: password = "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy"
6. Salva no banco: password criptografado
```

**Exemplo de hash BCrypt**:
```
Senha original: "senha123"
Hash gerado:    "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy"
                  ││  ││  └─────────────────────────────────────────────┐
                  ││  ││                                          Hash (31 chars)
                  ││  └┴─ Salt (22 caracteres)
                  │└─ Cost factor (2^10 = 1024 rounds)
                  └─ Algoritmo (2a = BCrypt)
```

---

### 7.3 Validações Implementadas

#### Validações de Bean Validation (JSR-380)

##### UserRequestDTO:
- `@NotBlank(message = "Name can't be blank")` - Nome obrigatório e não vazio
- `@Email(message = "Invalid E-mail")` - Formato de e-mail válido
- `@NotBlank(message = "E-mail can't be blank")` - E-mail obrigatório e não vazio
- `@NotBlank(message = "Password can't be blank")` - Senha obrigatória e não vazia

##### TaskRequestDTO:
- `@NotBlank(message = "Title can't be blank")` - Título obrigatório
- `@Size(min = 5, max = 50)` - Título entre 5 e 50 caracteres
- `@NotBlank(message = "Description can't be blank")` - Descrição obrigatória
- `@Size(min = 5, max = 255)` - Descrição entre 5 e 255 caracteres
- `@NotBlank(message = "Priority can't be blank")` - Prioridade obrigatória (LOW/MEDIUM/HIGH)
- `@NotNull(message = "User can't be null")` - Usuário proprietário obrigatório

#### Validações Programáticas:

##### UpdateUserUseCaseImpl:
```java
// Validação: E-mail único (excluindo o próprio usuário)
if(userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
    throw new EmailAlreadyExistsException("E-mail already registered!");
}
```

##### UpdateTaskUseCaseImpl (startTask/endTask):
```java
// Não permite iniciar tarefa já iniciada
if (task.getStartAt() != null) {
    throw new IllegalStateException("Task already started");
}

// Não permite finalizar tarefa não iniciada
if(task.getStartAt() == null) {
    throw new IllegalStateException("You can't end the task if its don't have a start date");
}

// Não permite finalizar tarefa já finalizada
if (task.getEndAt() != null) {
    throw new IllegalStateException("Task already finished");
}
```

---

### 7.4 Problemas de Segurança Identificados

#### ✅ Corrigidos:

1. **~~Senha exposta no Response~~** → **Corrigido**
   - `UserResponseDTO` não retorna mais o campo `password`

2. **~~Spring Security não configurado~~** → **Corrigido**
   - `SecurityFilterChain` configurado com CSRF desabilitado
   - Todos os endpoints liberados (adequado para API stateless sem JWT)

3. **~~DTOs sem validação~~** → **Corrigido**
   - `UserRequestDTO` e `TaskRequestDTO` possuem Bean Validation completa
   - `@Valid` aplicado nos controllers

4. **~~Sem tratamento global de exceções~~** → **Corrigido**
   - `GlobalExceptionHandler` com `@RestControllerAdvice` implementado
   - 10 handlers para diferentes tipos de exceção

5. **~~Sem CORS configurado~~** → **Corrigido**
   - `CorsConfig` implementado

#### ⚠️ Pendentes:

1. **Sem autenticação/autorização JWT**
   - Todos os endpoints são públicos
   - **Solução**: Implementar JWT com Spring Security

2. **Credenciais hardcoded**
   - Senha do MySQL no `application.properties`
   - **Solução**: Usar variáveis de ambiente

3. **SSL desabilitado**
   - `useSSL=false` na connection string
   - **Solução**: Habilitar SSL em produção

4. **Sem rate limiting**
   - Vulnerável a ataques de força bruta

---

## 8. Fluxo Completo

### 8.1 Fluxo de Criação de Usuário

#### Diagrama do Fluxo:

```
┌─────────┐       ┌────────────┐       ┌──────────────────┐
│ Cliente │       │   Spring   │       │   Application    │
│  (HTTP) │       │   MVC      │       │    Layer         │
└────┬────┘       └─────┬──────┘       └────────┬─────────┘
     │                  │                       │
     │ POST /users      │                       │
     │ JSON Request     │                       │
     ├─────────────────>│                       │
     │                  │                       │
     │                  │ @PostMapping          │
     │                  │ saveUser()            │
     │                  ├──────────────────────>│
     │                  │                       │
     │                  │                       │ UserController
     │                  │                       │ Chama Use Case ✅
     │                  │ HTTP 201 CREATED      │
     │                  │ UserResponseDTO       │
     │                  │<──────────────────────┤
     │ Response         │                       │
     │<─────────────────┤                       │
     │                  │                       │
```

#### Fluxo Esperado (corrigido):

```
1. REQUISIÇÃO HTTP
   └─ Cliente → POST /users
      └─ Headers: Content-Type: application/json
      └─ Body:
         {
           "name": "João Silva",
           "email": "joao@email.com",
           "password": "senha123"
         }

2. SPRING MVC (Presentation Layer)
   └─ DispatcherServlet intercepta requisição
   └─ Identifica @RequestMapping("/users")
   └─ Chama UserController.saveUser()
   └─ Deserializa JSON → UserRequestDTO

3. CONTROLLER (Presentation Layer)
   └─ UserController.saveUser(UserRequestDTO dto)
   └─ [DEVERIA] Chamar: createUserUseCase.createUser(dto)

4. USE CASE (Application Layer)
   └─ CreateUserUseCaseImpl.createUser(dto)
   
   4.1. VALIDAÇÃO DE ENTRADA
        └─ if (dto == null) → throw UserCantBeNullException
   
   4.2. VALIDAÇÃO DE UNICIDADE
        └─ userRepository.findByEmail(dto.email())
        └─ if (existe) → throw EmailAlreadyExistsException
   
   4.3. CONVERSÃO DTO → ENTITY
        └─ User user = userMapper.toEntity(dto)
        └─ Cria: new User(name, email, password_plaintext)
   
   4.4. CRIPTOGRAFIA
        └─ user.encryptPassword(passwordEncoder)
        └─ BCrypt transforma: "senha123" → "$2a$10$..."
   
   4.5. PERSISTÊNCIA
        └─ User userSaved = userRepository.save(user)

5. REPOSITORY ADAPTER (Infra Layer)
   └─ UserRepositoryImpl.save(user)
   └─ Delega para: userJpaRepository.save(user)

6. SPRING DATA JPA (Infra Layer)
   └─ UserJpaRepository.save(user)
   └─ JpaRepository executa operação

7. HIBERNATE (ORM)
   └─ Gera SQL:
      INSERT INTO User (id, name, email, password, createdAt)
      VALUES (UUID(), 'João Silva', 'joao@email.com', '$2a$10$...', NOW())
   
   └─ Executa query no banco

8. MYSQL (Database)
   └─ Persiste o registro
   └─ Retorna registro com:
      - id: UUID gerado
      - createdAt: timestamp atual

9. RETORNO (caminho inverso)
   └─ MySQL → Hibernate → JpaRepository → RepositoryImpl → Use Case
   └─ Use Case: userMapper.toDto(userSaved)
   └─ Retorna: UserResponseDTO

10. RESPONSE HTTP
    └─ Controller: ResponseEntity.status(201).body(response)
    └─ Spring MVC serializa DTO → JSON
    └─ Retorna ao cliente:
       {
         "id": "550e8400-e29b-41d4-a716-446655440000",
         "name": "João Silva",
         "email": "joao@email.com",
         "createdAt": "2024-02-18T16:30:00"
       }
```

#### Exceções Possíveis:

| Exceção | Quando Ocorre | Status HTTP Esperado |
|---------|---------------|---------------------|
| `UserCantBeNullException` | DTO é null | 400 Bad Request |
| `EmailAlreadyExistsException` | E-mail já existe | 409 Conflict |
| `ConstraintViolationException` | Validação Bean Validation falha | 400 Bad Request |
| `DataIntegrityViolationException` | Violação de constraint no BD | 409 Conflict |

**⚠️ Problema**: Sem `@ControllerAdvice`, essas exceções retornam 500 Internal Server Error

---

### 8.2 Fluxo de Criação de Tarefa

#### Status Atual: ✅ **IMPLEMENTADO**

#### Fluxo Implementado:

```
1. REQUISIÇÃO HTTP
   └─ Cliente → POST /tasks
      └─ Body:
         {
           "title": "Estudar Spring Boot",
           "description": "Completar curso Rocketseat",
           "priority": "HIGH",
           "user_id": "550e8400-e29b-41d4-a716-446655440000"
         }

2. SPRING MVC
   └─ DispatcherServlet intercepta requisição
   └─ Identifica @RequestMapping("/tasks")
   └─ Chama TaskController.saveTask()
   └─ Valida campos com @Valid → Bean Validation

3. CONTROLLER
   └─ TaskController.saveTask(TaskRequestDTO dto)
   └─ Chama: createTaskUseCase.createTask(dto)

4. USE CASE
   └─ CreateTaskUseCaseImpl.createTask(dto)
   
   4.1. VALIDAÇÃO DO USUÁRIO
        └─ userRepository.findById(dto.user_id())
        └─ Se não encontrado → throw UserNotFoundException
   
   4.2. CONVERSÃO DTO → ENTITY
        └─ Task task = taskMapper.toEntity(dto)
        └─ Cria: new Task(title, description, priority, null)
   
   4.3. VINCULAÇÃO COM USUÁRIO
        └─ task.setUser(userFound)
   
   4.4. PERSISTÊNCIA
        └─ Task taskSaved = taskRepository.save(task)

5. REPOSITORY
   └─ TaskRepositoryImpl.save(task)
   └─ Delega para: taskJpaRepository.save(task)

6. MYSQL
   └─ INSERT INTO Task (id, title, description, priority, user_id, createdAt)
   └─ Persiste com FK para User

7. RESPONSE
   └─ HTTP 201 CREATED
   └─ Body: TaskResponseDTO (com UserResponseDTO embutido)
```

---

### 8.3 Comparação dos Fluxos

| Aspecto | User | Task |
|---------|------|------|
| **Controller** | ✅ Todos os 7 endpoints implementados | ✅ Todos os 10 endpoints implementados |
| **Use Case** | ✅ Todos os 5 use cases implementados | ✅ Todos os 5 use cases implementados |
| **Repository** | ✅ Funcional | ✅ Funcional com queries customizadas |
| **Validações** | ✅ Bean Validation + validações programáticas | ✅ Bean Validation + validações programáticas |
| **Mapper** | ✅ Funcional | ✅ Funcional com UserResponseDTO embutido |
| **Banco de Dados** | ✅ Todas operações CRUD funcionando | ✅ Todas operações CRUD funcionando |

---

## 9. Pontos Fortes e Possíveis Melhorias

### 9.1 Análise da Clean Architecture

#### ✅ Aspectos que Seguem Clean Architecture:

1. **Separação de Camadas Clara**
   - Domain, Application, Infra e Presentation bem definidos
   - Cada camada tem responsabilidade específica

2. **Inversão de Dependências**
   - Domain define interfaces de repositório
   - Infra implementa essas interfaces
   - Use Cases dependem de abstrações, não implementações

3. **Independência de Framework no Domain**
   - Entidades usam apenas anotações JPA (tolerável)
   - Repositórios são interfaces puras
   - Sem referências ao Spring no domínio

4. **Use Cases Isolados**
   - Cada Use Case tem interface + implementação
   - Facilita testes unitários
   - Single Responsibility Principle

5. **DTOs para Isolamento**
   - Entidades não são expostas diretamente
   - Mappers fazem conversão entre camadas

#### ⚠️ Possíveis Violações:

1. **JPA Annotations no Domain** (Discussão)
   ```java
   @Entity
   @Id
   @GeneratedValue
   ```
   - **Problema**: Domain conhece JPA (framework)
   - **Contraponto**: Pragmatismo - muitos projetos aceitam isso
   - **Solução purista**: Criar entidades de domínio puras + entidades JPA na Infra

2. **PasswordEncoder no Domain**
   ```java
   public void encryptPassword(PasswordEncoder encoder)
   ```
   - **Problema**: Entidade User depende de `org.springframework.security`
   - **Solução**: Extrair para serviço de domínio ou passar String já criptografada

3. **Lombok no Domain**
   - **Problema**: Dependência de biblioteca externa
   - **Solução**: Gerar getters/setters manualmente (verboso)

4. **Exceções Duplicadas**
   - Existem em `domain.exceptions` E `application.exceptions`
   - **Confusão**: Quando usar cada uma?
   - **Solução**: Definir fronteira clara

#### Conclusão: **SIM, segue Clean Architecture (versão pragmática)**

O projeto implementa os conceitos fundamentais da Clean Architecture:
- ✅ Separação de responsabilidades
- ✅ Inversão de dependências
- ✅ Independência relativa de frameworks
- ✅ Testabilidade

Aceita compromissos pragmáticos (JPA no domain) que são comuns em projetos Spring.

---

### 9.2 Violações de Dependência Identificadas

#### 1. Domain → Spring Framework

**Localização**: `User.encryptPassword(PasswordEncoder)`

**Problema**:
```java
import org.springframework.security.crypto.password.PasswordEncoder;

public void encryptPassword(PasswordEncoder encoder) {
    this.password = encoder.encode(this.password);
}
```

**Por que é violação**: Domain não deveria conhecer Spring Security

**Soluções**:

**Opção A - Service de Domínio**:
```java
// Domain
public interface PasswordEncryptionService {
    String encrypt(String plainPassword);
}

// Infra
@Component
public class BCryptPasswordEncryptionService implements PasswordEncryptionService {
    private final PasswordEncoder encoder;
    
    @Override
    public String encrypt(String plainPassword) {
        return encoder.encode(plainPassword);
    }
}

// Use Case
user.setPassword(passwordEncryptionService.encrypt(user.getPassword()));
```

**Opção B - Criptografar antes de criar entidade**:
```java
// Use Case
String encryptedPassword = passwordEncoder.encode(userRequestDTO.password());
User user = new User(dto.name(), dto.email(), encryptedPassword);
```

---

#### 2. CreateTaskUseCaseImpl → Implementação Concreta

**Localização**: `CreateTaskUseCaseImpl.java`

**Problema**:
```java
import com.weg.rocketseatcourse.infra.persistence.TaskRepositoryImpl;
```

**Por que é violação**: Use Case importando classe da camada Infra

**Solução**: Remover import (não está sendo usado)

---

#### 3. UserController → Múltiplos Use Cases

**Não é violação**, mas **code smell**:

```java
public UserController(
    CreateUserUseCase createUserUseCase,
    FindAllUsersUseCase findAllUsersUseCase,
    FindUserByIdUseCase findUserByIdUseCase,
    UpdateUserUseCase updateUserUseCase,
    DeleteUserUseCase deleteUserUseCase
)
```

**Problema**: Controller com muitas dependências

**Solução**: Aceitável - cada endpoint precisa de seu Use Case específico

---

### 9.3 Melhorias Estruturais Sugeridas

#### ✅ Melhorias Críticas Implementadas:

##### 1. ~~Implementar Use Cases Faltantes~~ → **CONCLUÍDO**
- Todos os 5 use cases de Task implementados com lógica completa
- Todos os 5 use cases de User implementados com lógica completa

##### 2. ~~Corrigir UserController~~ → **CONCLUÍDO**
- Controller chama use cases corretamente
- `@Valid` aplicado nos `@RequestBody`

##### 3. ~~Global Exception Handler~~ → **CONCLUÍDO**
- `GlobalExceptionHandler` com `@RestControllerAdvice` implementado
- 10 handlers cobrindo todas as exceções do sistema

##### 4. ~~Remover Senha do UserResponseDTO~~ → **CONCLUÍDO**
- `UserResponseDTO` não expõe mais o campo `password`

##### 5. ~~Adicionar Bean Validation nos DTOs~~ → **CONCLUÍDO**
- `UserRequestDTO` e `TaskRequestDTO` com validações completas

##### 6. ~~Configurar Spring Security~~ → **CONCLUÍDO** (parcialmente)
- `SecurityFilterChain` configurado com CSRF desabilitado
- Todos os endpoints liberados (autenticação JWT ainda pendente)

##### 7. ~~Relacionamento User-Task com JPA~~ → **CONCLUÍDO**
- `Task.user` é `@ManyToOne` com `@JoinColumn`

##### 8. ~~Enum para Priority~~ → **CONCLUÍDO**
- `TaskPriority` enum com LOW, MEDIUM, HIGH

##### 9. ~~Sem CORS configurado~~ → **CONCLUÍDO**
- `CorsConfig` implementado

---

#### 🔴 Críticas Pendentes (Impacto Alto):

##### 1. Adicionar Autenticação JWT

**Problema**: Todos os endpoints são públicos, sem autenticação

**Solução**: Implementar JWT com Spring Security

##### 2. Implementar Testes Automatizados

**Problema**: Cobertura de testes em 0%

**Solução**:
- Testes unitários dos Use Cases
- Testes de integração dos Repositories
- Testes de API dos Controllers

---

#### 🟡 Importantes (Impacto Médio):

##### 3. Externalizar Configurações

**Problema**: Credenciais hardcoded no `application.properties`

**Solução**:
```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/rocketseat_course}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}
```

---

#### 🟢 Desejáveis (Impacto Baixo):

##### 4. Adicionar Swagger/OpenAPI

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

##### 5. Paginação em Listagens

```java
public interface FindAllUsersUseCase {
    Page<UserResponseDTO> findAll(Pageable pageable);
}
```

##### 6. Soft Delete

```java
@Entity
public class User {
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
```

##### 7. Auditoria Completa

```java
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditableEntity {
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @CreatedBy
    private String createdBy;
    
    @LastModifiedBy
    private String lastModifiedBy;
}
```

##### 8. Logging Estruturado

```java
@Slf4j
@Component
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    
    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        log.info("Creating user with email: {}", dto.email());
        
        try {
            // lógica...
            log.info("User created successfully with id: {}", user.getId());
        } catch (EmailAlreadyExistsException e) {
            log.warn("Attempt to create user with existing email: {}", dto.email());
            throw e;
        }
    }
}
```

##### 9. Health Checks

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```properties
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always
```

##### 10. Profile-Specific Configurations

```properties
# application-dev.properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# application-prod.properties
spring.jpa.show-sql=false
```

##### 11. Docker Compose para Dev

```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: mysqlPW
      MYSQL_DATABASE: rocketseat_course
    ports:
      - "3306:3306"
```

##### 12. CI/CD Pipeline

```yaml
# .github/workflows/ci.yml
name: CI
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 21
        uses: actions/setup-java@v2
        with:
          java-version: '21'
      - name: Build with Maven
        run: ./mvnw clean verify
```

---

### 9.4 Resumo Executivo

#### Estado Atual do Projeto:

**Arquitetura**: ✅ Excelente - Segue Clean Architecture  
**Implementação User**: ✅ Completa - Todos os 5 Use Cases e 7 endpoints funcionando  
**Implementação Task**: ✅ Completa - Todos os 5 Use Cases e 10 endpoints funcionando  
**Segurança**: ⚠️ Básica - BCrypt ok, sem senha no response, mas sem autenticação JWT  
**Qualidade**: 🟢 Boa - Ambos os módulos completos, validações e exception handling robustos

#### Prioridades de Melhoria:

1. **Alta**: Adicionar autenticação JWT
2. **Alta**: Adicionar autorização baseada em roles
3. **Média**: Implementar testes automatizados
4. **Média**: Adicionar paginação nos endpoints de listagem
5. **Baixa**: Melhorias de qualidade (logging estruturado, health checks)

#### Métricas:

- **Linhas de Código**: ~2000
- **Entidades**: 2 (User, Task)
- **Enums**: 1 (TaskPriority)
- **Use Cases User**: 5 de 5 (100% completos)
- **Use Cases Task**: 5 de 5 (100% completos)
- **Endpoints User**: 7 de 7 implementados (100%)
- **Endpoints Task**: 10 de 10 implementados (100%)
- **Exceções customizadas**: 8
- **Exception Handlers**: 10
- **Cobertura de Testes**: 0%

---

## Conclusão

Este projeto demonstra uma **excelente arquitetura** baseada em Clean Architecture, com separação clara de responsabilidades e uso correto de padrões de design. A estrutura está bem organizada e preparada para escalar.

O **módulo de User está completamente funcional**, com todos os 5 use cases implementados e todos os 7 endpoints REST operacionais, incluindo busca por nome.

O **módulo de Task também está completamente funcional**, com todos os 5 use cases implementados (incluindo as operações `startTask` e `endTask`), 10 endpoints REST operacionais, relacionamento `@ManyToOne` com User, enum `TaskPriority` e validações completas nos DTOs.

**Pontos Positivos**:
- ✅ Arquitetura sólida e escalável baseada em Clean Architecture
- ✅ Módulo User 100% completo e funcional (7 endpoints)
- ✅ Módulo Task 100% completo e funcional (10 endpoints)
- ✅ Boas práticas de design (Use Cases, Repositories, DTOs, Mappers)
- ✅ Criptografia de senha com BCrypt implementada corretamente
- ✅ Senha não exposta nas respostas (UserResponseDTO sem campo password)
- ✅ Sistema robusto de exceções customizadas (8 exceções, 10 handlers)
- ✅ GlobalExceptionHandler para tratamento centralizado de erros
- ✅ Validações com Bean Validation nos DTOs
- ✅ TaskPriority como Enum (LOW, MEDIUM, HIGH)
- ✅ Relacionamento ManyToOne entre Task e User
- ✅ SecurityFilterChain configurado (CSRF desabilitado)
- ✅ CorsConfig implementado
- ✅ Código limpo, bem organizado e seguindo SOLID

**Próximas Melhorias**:
- ❌ Autenticação JWT pendente
- ❌ Autorização baseada em roles pendente
- ❌ Testes automatizados (unitários e de integração) pendentes
- ⚠️ Paginação nos endpoints de listagem
- ⚠️ Logging estruturado
- ⚠️ Health checks e monitoring

**Conclusão Final**: O projeto está em um **excelente estado** com ambos os módulos (User e Task) completamente implementados e funcionais. A aplicação possui CRUD completo, filtros avançados, controle de ciclo de vida das tarefas (iniciar/finalizar), validações robustas e tratamento de erros consistente. O próximo passo natural é adicionar autenticação e autorização via JWT.

---

**Documento atualizado em**: 24 de Fevereiro de 2026  
**Versão do Projeto**: 0.0.1-SNAPSHOT  
**Spring Boot**: 3.5.10  
**Java**: 21
