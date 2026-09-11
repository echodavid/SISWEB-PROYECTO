package org.uv.validation;

import org.uv.model.Reporte;

public final class ReporteValidator {

    private ReporteValidator() {
    }

    public static String validate(Reporte reporte) {
        if (reporte == null) {
            return "El reporte es obligatorio.";
        }
        if (reporte.getTitulo() == null || reporte.getTitulo().trim().isEmpty()) {
            return "El título del reporte es obligatorio.";
        }
        if (reporte.getDescripcion() == null || reporte.getDescripcion().trim().isEmpty()) {
            return "La descripción es obligatoria.";
        }
        if (reporte.getUbicacion() == null || reporte.getUbicacion().trim().isEmpty()) {
            return "La ubicación es obligatoria.";
        }
        if (reporte.getPrioridad() == null || (!reporte.getPrioridad().equals("BAJA") && !reporte.getPrioridad().equals("MEDIA") && !reporte.getPrioridad().equals("ALTA"))) {
            return "La prioridad debe ser BAJA, MEDIA o ALTA.";
        }
        return null;
    }
}
