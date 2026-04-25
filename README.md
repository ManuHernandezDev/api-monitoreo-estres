# API REST - Monitoreo de Estrés Académico

## Descripción

API REST desarrollada con Spring Boot para el registro y gestión de información de estudiantes.
Este backend forma la base de un sistema orientado al monitoreo del estrés académico.

---

## Tecnologías

* Java 21
* Spring Boot
* PostgreSQL
* Docker
* Maven

---

## Ejecución del proyecto

### 1. Clonar repositorio

```bash
git clone https://github.com/ManuHernandezDev/api-monitoreo-estres.git
cd api-monitoreo-estres.git
```

### 2. Levantar base de datos

```bash
docker-compose up -d
```

### 3. Configuración

El proyecto utiliza variables de entorno para las credenciales de la base de datos.
Asegúrate de definir las siguientes variables antes de ejecutar la aplicación:

```
DB_USER
DB_PASSWORD
```

Archivo:

```
src/main/resources/application-dev.properties
```

Configuración:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5435/monitoreo_estres_db
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.profiles.active=dev
```

### 4. Ejecutar aplicación

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

---

## Endpoints

### Registro de estudiante

* Método: POST
* Endpoint: /api/v1/students/register
* Content-Type: application/json

#### Request

```json
{
  "email": "example@itoaxaca.edu.mx",
  "password": "hashed_password",
  "semester": 1,
  "origin": "Oaxaca",
  "sleepHours": 7
}
```

#### Response

```json
{
  "message": "Estudiante registrado exitosamente"
}
```

---

## Estado del proyecto

* Registro de estudiantes implementado
* Conexión a base de datos funcional
* Arquitectura base definida
