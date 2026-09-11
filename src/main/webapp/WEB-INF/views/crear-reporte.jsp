<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear reporte ciudadano</title>
</head>
<body>
    <h2>Registrar nuevo reporte</h2>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
    <form method="post" action="${pageContext.request.contextPath}/reportes">
        <label>Título:</label>
        <input type="text" name="titulo" value="${reporte.titulo}" required><br>

        <label>Descripción:</label>
        <textarea name="descripcion" rows="4" required>${reporte.descripcion}</textarea><br>

        <label>Ubicación:</label>
        <input type="text" name="ubicacion" value="${reporte.ubicacion}" required><br>

        <label>Prioridad:</label>
        <select name="prioridad">
            <option value="BAJA" ${reporte.prioridad == 'BAJA' ? 'selected' : ''}>Baja</option>
            <option value="MEDIA" ${reporte.prioridad == 'MEDIA' ? 'selected' : ''}>Media</option>
            <option value="ALTA" ${reporte.prioridad == 'ALTA' ? 'selected' : ''}>Alta</option>
        </select><br>

        <button type="submit">Guardar reporte</button>
    </form>
    <p><a href="${pageContext.request.contextPath}/reportes">Cancelar</a></p>
</body>
</html>
