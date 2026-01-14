# 📚 Bibliotech - Sistema de Gerenciamento de Biblioteca

O **Bibliotech** é um sistema completo para gestão de bibliotecas. Desenvolvido com uma arquitetura desacoplada, possui uma API robusta em Java e uma interface web intuitiva para o gerenciamento de livros, autores, gêneros, usuários e empréstimos.

---

## 🚀 Funcionalidades

- **Gestão de Acervo:** Cadastro e listagem de livros, autores e gêneros.
- **Controle de Usuários:** Cadastro de leitores.
- **Movimentações:** Realização de empréstimos e reservas de títulos.
- **Banco de Dados:** Persistência de dados segura com MySQL.

---

## 🛠️ Tecnologias Utilizadas

### **Backend**
- **Java 17+**
- **Spring Boot**: Framework principal.
- **Spring Data JPA**: Para persistência de dados.
- **Maven**: Gerenciador de dependências.
- **MySQL**: Banco de dados relacional.

### **Frontend**
- **HTML5** e **CSS3**: Estrutura e estilização moderna.
- **JavaScript**: Lógica de consumo da API (Fetch API).

---

## 📋 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
- [Git](https://git-scm.com)
- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- [MySQL Server](https://dev.mysql.com/downloads/installer/)
- Uma IDE de sua preferência (VS Code, IntelliJ, Eclipse)

---

## 🔧 Como rodar o projeto

### 1. Configuração do Banco de Dados
No MySQL, crie o banco de dados:
```sql


CREATE DATABASE bibliotech;

```
### 2. No arquivo src/main/resources/application.properties (dentro da pasta BibliotecaAPI), ajuste as credenciais conforme seu ambiente:

```
Properties

spring.datasource.url=jdbc:mysql://localhost:3306/bibliotech
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### 3. Executando o Backend
Abra o terminal na pasta BibliotecaAPI e execute:

```
Bash

mvn spring-boot:run

```
A API estará rodando em http://localhost:8080.

### 4. Executando o Frontend
Navegue até a pasta Bibliotecahtml e abra o arquivo index.html no seu navegador.


## 📂 Organização do Repositório

O projeto está estruturado em duas pastas principais para separar as responsabilidades de servidor e interface:

* **`/BibliotecaAPI`**: Pasta que contém o ecossistema **Backend**.
    * Configurações do Spring Boot.
    * Modelos de dados (Entities) e Repositories (JPA).
    * Controllers que disponibilizam os Endpoints da API.
    * Arquivo `pom.xml` com as dependências do Maven.

* **`/Bibliotecahtml`**: Pasta que contém o **Frontend**.
    * `index.html`: Página principal do sistema.
    * Arquivos **CSS**: Estilização visual das tabelas e formulários.
    * Arquivos **JavaScript**: Lógica de integração que faz as chamadas `fetch` para a API Java.

---

## ✒️ Autor

Desenvolvido com dedicação por **Matheus Santos**.

* **GitHub:** [@matheussantos8](https://github.com/matheussantos8)
* **LinkedIn:** [matheussantos](https://www.linkedin.com/in/matheus-santos-869333318/)
* **Email:** matheussantosdasilva282@gmail.com
