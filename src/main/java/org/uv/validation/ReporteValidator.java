package org.uv.validation;

import java.util.Locale;

import org.uv.model.Reporte;

public final class ReporteValidator {

    private ReporteValidator() {
    }

    public static void normalizar(Reporte reporte) {
        if (reporte == null) {
            return;
        }

        if (reporte.getTitulo() != null) {
            reporte.setTitulo(reporte.getTitulo().trim());
        }
        if (reporte.getDescripcion() != null) {
            reporte.setDescripcion(reporte.getDescripcion().trim());
        }
        if (reporte.getUbicacion() != null) {
            reporte.setUbicacion(reporte.getUbicacion().trim());
        }

        String prioridad = reporte.getPrioridad();
        if (prioridad == null || prioridad.trim().isEmpty()) {
            reporte.setPrioridad("MEDIA");
            return;
        }

        reporte.setPrioridad(prioridad.trim().toUpperCase(Locale.ROOT));
    }

    public static String validate(Reporte reporte) {
        if (reporte == null) {
            return "El reporte es obligatorio.";
        }

        normalizar(reporte);

        if (reporte.getTitulo() == null || reporte.getTitulo().trim().isEmpty()) {
            return "El título del reporte es obligatorio.";
        }
        if (reporte.getDescripcion() == null || reporte.getDescripcion().trim().isEmpty()) {
            return "La descripción es obligatoria.";
        }
        if (reporte.getUbicacion() == null || reporte.getUbicacion().trim().isEmpty()) {
            return "La ubicación es obligatoria.";
        }
        if (!"BAJA".equals(reporte.getPrioridad()) && !"MEDIA".equals(reporte.getPrioridad()) && !"ALTA".equals(reporte.getPrioridad())) {
            return "La prioridad debe ser BAJA, MEDIA o ALTA.";
        }
        return null;
    }
}
