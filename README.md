# FakeStore UI

Interface web para consulta de pedidos e produtos, construída com **Spring Boot**, **PrimeFaces**, **JoinFaces** e **PrimeFlex**.

## Funcionalidades

- Pesquisa de pedidos por cliente, data e número do pedido
- Listagem de pedidos e produtos conforme filtros
- Layout responsivo e moderno com PrimeFlex

## Pré-requisitos

- Java 17+
- Maven 3.8+
- [Rodar primeiro backend API](https://github.com/tiag188/samsung-fakestore-api)

## Como rodar

1. **Clone o repositório:**

   ```sh
   git clone <url-do-repositorio>
   cd samsung-fakestore-ui
   ```

2. **Compile e gere o JAR:**

   ```sh
   mvn clean package
   ```

3. **Execute a aplicação:**

   ```sh
   java -jar target/samsung-fakestore-ui-1.0.0.jar
   ```

4. **Acesse no navegador:**
   - [http://localhost:8080/index.xhtml](http://localhost:8080/index.xhtml)

## Estrutura do Projeto

- `src/main/resources/META-INF/resources/` — arquivos `.xhtml` das telas
- `src/main/java/com/samsung/fakestore/bean/` — beans JSF/Spring
- `src/main/java/com/samsung/fakestore/dto/` — DTOs de Pedido, Produto, Cliente
- `src/main/java/com/samsung/fakestore/service/` — serviços de negócio/mock

## Customização de Tema

O tema PrimeFaces pode ser alterado em `src/main/resources/application.yml`:

```yaml
joinfaces:
  primefaces:
    theme: saga
```

## Layout Responsivo

O projeto utiliza [PrimeFlex](https://www.primefaces.org/primeflex/) para facilitar o layout flexível e responsivo.

## Observações

- Os dados de pedidos/produtos são mockados no serviço (`OrderService`).
- Para integração real, adapte o serviço para buscar dados de uma API ou banco de dados.
- Não é necessário Payara, Tomcat ou outro servidor externo.

---

## Estrutura do projeto

```
samsung-fakestore-api/     # Backend Spring Boot
samsung-fakestore-ui/      # Frontend PrimeFaces
```
