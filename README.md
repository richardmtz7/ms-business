# ms-business
This a application like a lite ecommerce developed in Java with Sring Boot. The application  allows the manage of categries, companies, orders and products atrought an API REST.

## Characteristics

- **Categories managment**: Create, Get and delete categories.
- **Companies managment**: Create, edit and activate/deactivate companies.
- **Orders managment**: Create, edit and delete orders.
- **Products managment**: Create, get and delete products.

## Applied Technologies

- **Java 17**
- **Spring Boot**
- **JPA/Hibernate**
- **MySQL**
- **Maven** for dependencies


## Instalación

1. Clone repository:
   ```bash
   git clone https://github.com/tu_usuario/ecommerce-lite.git
2. Navigate to the project folder:
   ```bash
   cd ecommerce-lite
3. Config the databases in the application.properties file.
4. Compile the project:
   ```bash
   mvn clean install
5. Excecute app:
   ```bash
   mvn spring-boot:run
## API usage
The API can be consumed through tools like Postman or cURL. Here some examples of available endpoints:

- Create category
  - **POST /api/business/category/create**
. Get categories
  - **POST /api/business/category/getAll**
 
The endpoints can be found in the next folder: com.ecommerce.lite.controllers
