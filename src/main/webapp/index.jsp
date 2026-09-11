<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PR07 - Reportes ciudadanos</title>
</head>
<body>
    <h1>Sistema de reportes ciudadanos</h1>
    <p>Proyecto PR07 con JSP, Servlet, Tomcat y PostgreSQL.</p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/reportes">Ver reportes</a></li>
        <li><a href="${pageContext.request.contextPath}/reportes?accion=nuevo">Crear reporte</a></li>
    </ul>
</body>
</html>
