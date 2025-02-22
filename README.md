
![Events2](https://github.com/user-attachments/assets/be5f52d3-da7f-45b9-9868-9b3c76dbb4eb)

# 📌 API Rest - Inscrições em Eventos.  

**Este projeto foi desenvolvido com fins estudantis em conjunto com o evento NLW Connect** e tem como objetivo facilitar o processo de inscrições e indicações. Com ele, os participantes podem se cadastrar, indicar amigos e acompanhar um ranking de indicações, tornando a experiência de participação no evento ainda mais interativa e envolvente.

## 🚀 Sobre o Projeto  

O Sistema de Inscrições em Eventos possibilita que os organizadores criem eventos e que os usuários realizem suas inscrições de maneira prática e intuitiva. Cada inscrito recebe um link exclusivo para indicar outras pessoas, e aqueles com o maior número de indicações aparecem em um ranking de destaques!

🔎 Quer saber mais sobre os **requisitos funcionais, user stories e detalhes do projeto**?  
Acesse o documento completo aqui: [Mais informações sobre o projeto](https://sassy-ermine-3c7.notion.site/Events-API-Rest-1a212b6797b3805b96a4cc83f9803451?pvs=73)  

### 🟣 Funcionalidades  

✅ Cadastro de eventos com nome, localidade, preço, data e hora de inicio e fim   
✅ Inscrição de participantes com nome e e-mail  
✅ Geração de link único para indicar amigos  
✅ Ranking de indicações atualizado em tempo real  

## 🛠 Tecnologias Utilizadas  

O projeto foi desenvolvido com as seguintes tecnologias:  

- **Java com Spring Boot** (para a API REST)  
- **Spring JPA** (para manipulação do banco de dados)  
- **MySQL** (para armazenar as informações)  
- **Maven** (para gerenciamento de dependências)  
- **Insomnia** (para testar os endpoints da API)  

## 🎯 Como Rodar o Projeto  

### 🔹 Pré-requisitos  
Antes de começar, você precisa ter instalado:  
- **Java 21**  
- **Maven**  
- **MySQL**  
- **Insomnia (opcional, mas recomendado para testes)**  

### 🔹 Passo a Passo  

1️⃣ **Clone o repositório:**  
```bash
git clone https://github.com/alvaromottadev/events-api-rest.git
```  

2️⃣ **Entre na pasta do projeto:**  
```bash
cd events-api-rest
```  

3️⃣ **Configure o banco de dados**  
- Crie um banco de dados no MySQL com o nome `db_events`
- No arquivo `application.properties`, configure as credenciais do MySQL:  
  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/db_events
  spring.datasource.username=seu_usuario
  spring.datasource.password=sua_senha
  ```  

4️⃣ **Crie as tabelas no banco de dados**  
- Para criar as tabelas necessárias, execute o SQL disponível neste link:  
  👉 [Query SQL para criação das tabelas](https://economic-jaborosa-ec9.notion.site/Query-SQL-API-Events-1a1268a7953a80488c22f2f2f1fca64f)  

5️⃣ **Instale as dependências e rode o projeto:**  
```bash
mvn clean install
mvn spring-boot:run
```  

6️⃣ **Testando a API**  
- Para testar os endpoints, você pode usar o **Insomnia** ou qualquer ferramenta similar.  

## 💡 Contribuição  

Se quiser melhorar algo ou adicionar mais funcionalidades, fique à vontade para abrir um pull request ou sugerir mudanças! Obrigado 😊  
