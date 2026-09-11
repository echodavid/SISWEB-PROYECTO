package org.uv.controller;

import org.uv.dao.ReporteDAO;
import org.uv.model.Reporte;
import org.uv.validation.ReporteValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/reportes")
public class ReporteServlet extends HttpServlet {

    private final ReporteDAO reporteDAO = new ReporteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("nuevo".equalsIgnoreCase(accion)) {
            request.getRequestDispatcher("/WEB-INF/views/crear-reporte.jsp").forward(request, response);
            return;
        }

        try {
            List<Reporte> reportes = reporteDAO.listar();
            request.setAttribute("reportes", reportes);
            request.getRequestDispatcher("/WEB-INF/views/reportes.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("No se pudo consultar la base de datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String ubicacion = request.getParameter("ubicacion");
        String prioridad = request.getParameter("prioridad");

        Reporte reporte = new Reporte();
        reporte.setTitulo(titulo);
        reporte.setDescripcion(descripcion);
        reporte.setUbicacion(ubicacion);
        reporte.setPrioridad(prioridad);

        String error = ReporteValidator.validate(reporte);
        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("reporte", reporte);
            request.getRequestDispatcher("/WEB-INF/views/crear-reporte.jsp").forward(request, response);
            return;
        }

        try {
            reporteDAO.guardar(reporte);
            response.sendRedirect(request.getContextPath() + "/reportes");
        } catch (SQLException e) {
            throw new ServletException("No se pudo registrar el reporte", e);
        }
    }
}
