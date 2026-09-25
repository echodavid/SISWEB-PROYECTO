# PR07 - Sistema de reportes ciudadanos

## Objetivo
Implementar un sistema web de reportes ciudadanos con JSF, PrimeFaces, PostgreSQL y contenedores Docker. La aplicación permite registrar, validar y listar reportes con una base de datos local reproducible.

## Requisitos
- Docker Desktop o Docker Engine
- Docker Compose
- Java 11 (solo para compilar localmente si se desea)
- Maven 3.8+ (solo para compilar localmente)

## Stack
- Java 11
- Maven
- JSF 2.3
- PrimeFaces 12
- PostgreSQL 16
- Tomcat 9

## Arquitectura con Docker
El proyecto usa un build multi-stage:
1. Se compila el WAR con Maven.
2. Se despliega en Tomcat.
3. Se levanta PostgreSQL en un servicio separado dentro del mismo compose.

## Arranque rápido
Desde la raíz del proyecto:

```bash
docker compose up --build -d
```

Esto levanta:
- PostgreSQL en `localhost:5432`
- la aplicación en `http://localhost:8080/index.xhtml`

## Variables de entorno
La app usa estas variables cuando corre dentro de Docker:

```bash
DB_URL=jdbc:postgresql://postgres:5432/pr07_db
DB_USER=postgres
DB_PASSWORD=postgres
```

## Base de datos
Se crea automáticamente en el servicio `postgres` usando el script:

- [db/schema.sql](db/schema.sql)

## Compilación local
```bash
mvn clean package
```

Se genera el artefacto:

```bash
target/pr07-project.war
```

## Flujo principal
1. Abrir la aplicación en `http://localhost:8080/index.xhtml`
2. Ir a "Nuevo reporte"
3. Registrar título, descripción, ubicación y prioridad
4. Validar los datos
5. Ver la lista en la vista de reportes

## Validaciones del negocio
- Válido: título, descripción y ubicación completos; prioridad `ALTA`
- Inválido: título vacío, descripción vacía, ubicación vacía o prioridad no válida

## Evidencia técnico-reproducible
- [pom.xml](pom.xml)
- [docker-compose.yml](docker-compose.yml)
- [Dockerfile](Dockerfile)
- [db/schema.sql](db/schema.sql)
- [src/main/java/org/uv/dao/ReporteDAO.java](src/main/java/org/uv/dao/ReporteDAO.java)
- [src/main/java/org/uv/bean/ReporteBean.java](src/main/java/org/uv/bean/ReporteBean.java)
- [src/test/java/org/uv/validation/ReporteValidatorTest.java](src/test/java/org/uv/validation/ReporteValidatorTest.java)

## Resultado esperado
La aplicación web funciona con PostgreSQL dentro del mismo `docker-compose.yml` y se despliega con un Dockerfile multi-stage, sin depender de una instalación local de Tomcat ni de una base externa.
