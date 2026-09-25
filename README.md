# PR07 / M03 / P03 - Sistema de reportes ciudadanos

## Objetivo
Evolucionar el proyecto PR07 a una aplicación Web 2.0 con JSF y PrimeFaces, conservando el problema de reportes ciudadanos, la trazabilidad del modelo y la validación de negocio con PostgreSQL.

## Alcance del incremento
- Formulario con validación de negocio.
- Lista de reportes con componente PrimeFaces.
- Persistencia en PostgreSQL.
- Mensajes comprensibles y flujo reproducible.
- Eliminación de elementos residuales del proyecto anterior para mantener limpieza técnica.

## Roles funcionales
- Ciudadano: registra incidencias.
- Operador: revisa y prioriza.
- Responsable: da seguimiento y resuelve el caso.

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
- Tomcat 9+

## Base de datos local
Desde la raíz del proyecto:

```bash
docker compose up -d
```

Configuración de la base:
- Base: `pr07_db`
- Usuario: `postgres`
- Contraseña: `postgres`
- Puerto: `5432`

## Compilación
```bash
cd SIS_WEB/proyecto
mvn clean package
```

Artefacto generado:

```bash
target/pr07-project.war
```

## Despliegue en Tomcat
1. Copiar el WAR a `webapps`.
2. Iniciar Tomcat.
3. Abrir:

```text
http://localhost:8080/pr07-project/index.xhtml
```

## Flujo principal
1. Ingresar a la vista de inicio.
2. Seleccionar "Nuevo reporte".
3. Registrar título, descripción, ubicación y prioridad.
4. Validar que los datos sean correctos.
5. Confirmar el guardado y revisar la tabla de reportes.

## Validaciones positivas y negativas
- Positiva: título, descripción y ubicación completos; prioridad `ALTA`.
- Negativa: título vacío, descripción vacía, ubicación vacía o prioridad no válida.

## Evidencia reproducible
- [pom.xml](pom.xml)
- [docker-compose.yml](docker-compose.yml)
- [db/schema.sql](db/schema.sql)
- [src/main/java/org/uv/bean/ReporteBean.java](src/main/java/org/uv/bean/ReporteBean.java)
- [src/main/java/org/uv/dao/ReporteDAO.java](src/main/java/org/uv/dao/ReporteDAO.java)
- [src/main/java/org/uv/validation/ReporteValidator.java](src/main/java/org/uv/validation/ReporteValidator.java)
- [src/test/java/org/uv/validation/ReporteValidatorTest.java](src/test/java/org/uv/validation/ReporteValidatorTest.java)

## Resultado esperado
La aplicación ejecuta una vista JSF con PrimeFaces, valida entradas, persiste en PostgreSQL y presenta una interfaz consistente para el flujo principal del PR07.
