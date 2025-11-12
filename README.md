# Desafio Dock

API REST desenvolvida com **Spring Boot 3**, **Java 21** e **H2 Database**, simulando um sistema bancário com operações de **Portadores, Contas e Transações**.  

---

## Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database (em memória)**
- **Lombok**
- **Maven**
- **Docker (opcional)**
- **Postman (para testes)**

---
O proeto foi criado para rodar em container docker. Segue com arquivo docker-compose com a criação dos container necessários e o Dockerfile para gerar a imagem da aplicação.

### Rodar o projeto
mvn spring-boot:run


### A aplicação iniciará em:
 http://localhost:8080

## Banco de dados

O projeto utiliza o H2 Database, um banco em memória ideal para testes e desenvolvimento.
Mas foi configurado para usar postgree caso queira. Só mudar a flag no arquivo application.poperties de test para dev.

## Console H2

### Acesse o console do H2 no navegador:
http://localhost:8080/h2-console

## Configurações de acesso:

Driver Class	org.h2.Driver
JDBC URL	jdbc:h2:mem:testdb
User Name	sa
Password	(vazio)

Foi feito seeding com dados fictícios para teste

