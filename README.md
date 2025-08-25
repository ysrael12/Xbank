# Xbank

Xbank é um sistema bancário web desenvolvido em Java com Spring Boot e Thymeleaf. Permite aos usuários gerenciar contas, realizar transferências, consultar saldo, extrato e gerenciar cartões de crédito.

## Funcionalidades
- Cadastro e autenticação de usuários
- Visualização de saldo e extrato da conta
- Transferências (Pix, TED, Boleto, Depósito)
- Gerenciamento de cartões de crédito
- Interface web responsiva

## Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Thymeleaf
- Maven
- SQLite (bancox.db)
- Flowbite CSS

## Como Executar

### Pré-requisitos
- Java JDK 17 ou superior
- Maven 3.6+

### Passos para rodar o projeto
1. Clone o repositório:
   ```bash
   git clone <repo-url>
   cd Xbank
   ```
2. Compile o projeto:
   ```bash
   mvn clean install
   ```
3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
   O sistema estará disponível em [http://localhost:8000](http://localhost:8000)

## Configuração
- O arquivo de configuração está em `src/main/resources/application.properties`.
- O banco de dados SQLite está em `src/main/resources/bancox.db`.

## Estrutura do Projeto
```
Xbank/
├── src/
│   ├── main/
│   │   ├── java/com/xbank/         # Código Java
│   │   └── resources/
│   │       ├── templates/          # Templates Thymeleaf
│   │       └── application.properties
│   └── test/
├── pom.xml                         # Configuração Maven
```

## Personalização
- Para alterar o banco de dados, edite o `application.properties`.
- Para modificar a interface, edite os arquivos HTML em `src/main/resources/templates/`.

## Suporte
Para dúvidas ou problemas, abra uma issue no repositório.

---
Projeto para fins educacionais.