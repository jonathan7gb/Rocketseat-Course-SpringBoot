# Rocketseat Course - Spring Boot

Projeto desenvolvido no curso da Rocketseat sobre Spring Boot, implementando uma aplicação com Clean Architecture.

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

## 📝 Endpoints Disponíveis

### Users
- `POST /users` - Criar novo usuário

### Tasks
- ⚠️ Em desenvolvimento

**Nota**: A maioria dos endpoints ainda está em implementação. Consulte [ANALISE-TECNICA.md](./ANALISE-TECNICA.md) para detalhes do status de cada funcionalidade.

## 📖 Aprendizados do Curso

Este projeto demonstra a implementação de:

- ✅ Clean Architecture com Spring Boot
- ✅ Separação em camadas (Domain, Application, Infra, Presentation)
- ✅ Padrão Repository
- ✅ Use Cases para regras de negócio
- ✅ DTOs e Mappers
- ✅ Criptografia de senhas com BCrypt
- ✅ Validações com Bean Validation
- ✅ Spring Data JPA

## 🔒 Segurança

- Senhas criptografadas com **BCrypt**
- Validações de entrada nos DTOs
- Unicidade de e-mail garantida

## 📈 Status do Projeto

**Versão**: 0.0.1-SNAPSHOT

**Status de Implementação**:
- ✅ Arquitetura definida e estruturada
- ✅ CreateUserUseCase completo
- ⚠️ Demais use cases em desenvolvimento
- ⚠️ Endpoints em implementação

Para uma visão completa do status, melhorias sugeridas e próximos passos, consulte [ANALISE-TECNICA.md](./ANALISE-TECNICA.md).

## 👨‍💻 Autor

Projeto desenvolvido durante o curso Rocketseat - Spring Boot

## 📄 Licença

Este projeto é para fins educacionais.
