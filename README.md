# Car GraphQL API

> This is a simple GraphQL model API written in JAVA 12 with [Spring Boot](https://spring.io/projects/spring-boot) and [GraphQL Schema Publisher & Query Resolver - SPQR](https://github.com/leangen/graphql-spqr).

## Environment Provisioning

- Download the project: `$ git clone https://github.com/euiagosilva/car-graphql-api.git`
- Solve dependencies: `$ ./gradlew clean build -x test`
- Testing execution: `$ ./gradlew test`
- Install [Docker](https://docs.docker.com/install/) and [Docker Compose](https://docs.docker.com/compose/install/) in your OS;
- Create the postgres database container: `$ docker-compose -f docker/docker-compose-postgres.yml up`

## Run Application

- Execute: `$ ./gradlew bootRun`
- Access: `http://localhost:8081/graphiql` 

## Executing Operations

#### Mutations

- **Save a Car:**
    ```
    mutation {
       saveCar(car: {
         brand: "BMW"
         color: "Black"
         description: "BMW M5 First Edition inspired by BMW Individual"
         price: 78.890
         year: 2017
         model: "M5 Sedan"
         isNew: false
       }) {
           id
       }
    }
    ```

- **Update a Car:**
    ```
    mutation {
       updateCar(car: {
         id: 1
         brand: "BMW"
         color: "Yellow"
         description: "BMW M5 First Edition inspired by BMW Individual"
         price: 78.890
         year: 2018
         model: "M5 Sedan"
         isNew: false
       }) {
           id
       }
    }
    ```

- **Deletes a car by id:**
    ```
    mutation {
      deleteCar(id: 1)
    }
    ```

### Queries

- **Gets all cars:**
    ```
    {
       cars {
         id
         brand
         color
       }
    }
    ```
    
- **Gets a car by id:**
    ```
    { 
       car(id: 1) {
         price
         year
       }
    }
    ```

## REST Support

It's possible perform REST operations on API using [Swagger UI](https://swagger.io/tools/swagger-ui/). Only access: `http://localhost:8081/swagger-ui.hml`;

## Meta

[Iago Paixão](https://www.linkedin.com/in/iagopaixao/) – euiagopaixao@gmail.com

Distribuído sob a licença [MIT](https://github.com/euiagosilva/car-graphql-api/blob/master/LICENSE).

https://github.com/euiagosilva/car-graphql-api


