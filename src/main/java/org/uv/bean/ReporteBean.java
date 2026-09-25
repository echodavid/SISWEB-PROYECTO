package org.uv.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.uv.dao.ReporteDAO;
import org.uv.model.Reporte;
import org.uv.validation.ReporteValidator;

@ManagedBean(name = "reporteBean")
@ViewScoped
public class ReporteBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Reporte reporte;
    private List<Reporte> reportes;
    private final ReporteDAO reporteDAO;

    public ReporteBean() {
        this.reporte = new Reporte();
        this.reportes = new ArrayList<>();
        this.reporteDAO = new ReporteDAO();
        cargarReportes();
    }

    public void guardar() {
        ReporteValidator.normalizar(reporte);
        String error = ReporteValidator.validate(reporte);
        if (error != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación", error));
            return;
        }

        try {
            reporte.setUsuarioId(1);
            reporte.setCategoriaId(1);
            reporte.setEstadoId(1);
            reporte.setPrioridad(reporte.getPrioridad() == null ? "MEDIA" : reporte.getPrioridad());
            reporteDAO.guardar(reporte);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Reporte registrado", "El caso fue guardado correctamente."));
            reporte = new Reporte();
            cargarReportes();
        } catch (SQLException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de persistencia", "No se pudo guardar el reporte."));
        }
    }

    public void cargarReportes() {
        try {
            this.reportes = reporteDAO.listar();
        } catch (SQLException e) {
            this.reportes = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de consulta", "No se pudieron cargar los reportes."));
        }
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }
}
