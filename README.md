# Xbank

Xbank é um sistema bancário web desenvolvido em Java com Spring Boot e Thymeleaf. Permite aos usuários gerenciar contas, realizar transferências, consultar saldo, extrato e gerenciar cartões de crédito.

## Feito por 
-  HUGO SANTOS DA PAIXÃO (UFS)
-  YSRAEL DE JESUS SACRAMENTO (UFS)

## Funcionalidades
- Cadastro e autenticação de usuários
- Visualização de saldo e extrato da conta
- Transferências (Pix, TED, Boleto, Depósito)
- Gerenciamento de cartões de crédito
- Interface web responsiva

## Padrões de projeto / Arquitetura
- MVC (Model-View-Controller)
- Padrão factory para criação de objetos
- Padrão Repository para acesso a dados
- Padrão Service para lógica de negócio
- Models para representar entidades
- Repositórios para acesso a dados
- Serviços para lógica de negócio
- Controladores para gerenciar requisições HTTP
- Templates Thymeleaf para renderização de views
- Segurança com Spring Security
- Banco de dados SQLite para armazenamento de dados


## Requisitos do Projeto 
- Pelo menos 1 teste usando JUnit (X)
- Usar persistência de dados (X)
- Usar Spring Boot (X)
- Usar pelo menos 1 classe abstrata ou
interface (X)
- Usar pelo menos 1 padrão de projeto (X)



## Guia Rápido
- Acesse a aplicação via navegador em [http://localhost:8000](http://localhost:8000)
- Faça login com suas credenciais ou cadastre-se para criar uma nova conta
- crie uma conta pessoa física ou jurídica 
- Navegue pela navbar para acessar diferentes funcionalidades
- Com a conta criada, você pode fazer transferências, consultar saldo e extrato, e gerenciar seus cartões de crédito
- Com sua conta você tem acesso ao seu perfil de conta onde você pode ver suas informações pessoais e de conta
- Na seção de cartões, você pode adicionar, visualizar e gerenciar seus cartões de crédito vinculados à sua conta
- Na seçao de transferências, você pode realizar transferências via Pix, TED, Boleto e Depósito
- Para ver as listas de contas criadas você pode acessar o seguinte link [http://localhost:8000/admin/accounts](http://localhost:8000/admin/accounts)

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
   ### HTTP

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
