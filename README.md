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
git clone <url-del-repositorio>
cd <nombre-del-proyecto>
```

### 2. Levantar base de datos

```bash
docker-compose up -d
```

### 3. Configuración

Archivo:

```
src/main/resources/application-dev.properties
```

Configuración:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5435/monitoreo_estres_db
spring.datasource.username=admin_ito
spring.datasource.password=root

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
  "origin": "Local",
  "sleepHours": 7,
  "privacyAccepted": true
}
```

#### Response

```json
{
  "message": "Student registered successfully"
}
```
---
## Validaciones implementadas
* El correo debe ser institucional (@itoaxaca.edu.mx)
* El semestre debe estar entre 1 y 4
* Las horas de sueño deben estar entre 0 y 20
* No se permiten valores negativos
* El usuario debe aceptar el aviso de privacidad
---
## Estado del proyecto
* Registro de estudiantes implementado
* Validaciones de dominio completas
* Integración con base de datos funcional
* Arquitectura hexagonal implementada
* Pruebas unitarias con Mockito
* Pruebas de integración con Spring Boot
