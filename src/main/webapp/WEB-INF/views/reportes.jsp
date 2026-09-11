<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reportes ciudadanos</title>
</head>
<body>
    <h2>Listado de reportes</h2>
    <a href="${pageContext.request.contextPath}/reportes?accion=nuevo">Nuevo reporte</a>
    <table border="1" cellpadding="6">
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Ubicación</th>
            <th>Prioridad</th>
            <th>Descripción</th>
        </tr>
        <c:forEach var="reporte" items="${reportes}">
            <tr>
                <td>${reporte.id}</td>
                <td>${reporte.titulo}</td>
                <td>${reporte.ubicacion}</td>
                <td>${reporte.prioridad}</td>
                <td>${reporte.descripcion}</td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="${pageContext.request.contextPath}/">Volver al inicio</a></p>
</body>
</html>
