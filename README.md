# PR07 - Sistema de reportes ciudadanos

## Objetivo
Implementar un sistema web de reportes ciudadanos con JSP, Servlet, Tomcat y PostgreSQL, conforme a la ficha PR07 y al alcance de F02/P02.

## Roles funcionales
- Ciudadano: registra reportes.
- Operador: revisa y da seguimiento.
- Responsable: atiende la resolución del caso.

## Entidades principales
1. usuarios
2. categorias
3. estados
4. reportes
5. evidencias
6. comentarios

## Requisitos
- Java 11+
- Maven 3.8+
- Docker Desktop o Docker Engine
- Tomcat 9+ (opcional para desplegar WAR)

## Arranque rápido de la base PostgreSQL
Desde la raíz del proyecto:

```bash
docker compose up -d
```

Esto levanta PostgreSQL con:

- Base de datos: `pr07_db`
- Usuario: `postgres`
- Contraseña: `postgres`
- Puerto: `5432`

Se ejecuta el script [db/schema.sql](db/schema.sql) automáticamente al iniciar el contenedor.

## Verificar PostgreSQL
```bash
psql -h localhost -U postgres -d pr07_db
```

## Compilación del proyecto
```bash
cd SIS_WEB/proyecto
mvn clean package
```

Se genera:

```bash
target/pr07-project.war
```

## Despliegue en Tomcat
1. Copiar el WAR a la carpeta webapps de Tomcat.
2. Iniciar Tomcat.
3. Abrir:

```text
http://localhost:8080/pr07-project/
```

## Flujo principal
1. El ciudadano entra a la app.
2. Crea un reporte con título, descripción, ubicación y prioridad.
3. El sistema valida los datos.
4. Si es correcto, se guarda en PostgreSQL.
5. El listado muestra los reportes registrados.

## Validación positiva y negativa
- Positiva: título válido, descripción completa, ubicación válida, prioridad = ALTA.
- Negativa: título vacío, descripción vacía, ubicación vacía, prioridad = URGENTE.

## Evidencia reproducible
- [pom.xml](pom.xml)
- [docker-compose.yml](docker-compose.yml)
- [db/schema.sql](db/schema.sql)
- [src/main/java/org/uv/controller/ReporteServlet.java](src/main/java/org/uv/controller/ReporteServlet.java)
- [src/main/java/org/uv/dao/ReporteDAO.java](src/main/java/org/uv/dao/ReporteDAO.java)
- [src/main/java/org/uv/validation/ReporteValidator.java](src/main/java/org/uv/validation/ReporteValidator.java)
- [src/test/java/org/uv/validation/ReporteValidatorTest.java](src/test/java/org/uv/validation/ReporteValidatorTest.java)

## Resultado esperado
La aplicación permite registrar reportes ciudadanos con validación y persistencia en PostgreSQL, y la base puede levantarse de manera reproducible con Docker Compose.
# SISWEB-PROYECTO
