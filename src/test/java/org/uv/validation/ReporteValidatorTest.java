package org.uv.validation;

import org.junit.Assert;
import org.junit.Test;
import org.uv.model.Reporte;
import org.uv.util.DatabaseConfig;

public class ReporteValidatorTest {

    @Test
    public void debeUsarUrlPorDefectoDePostgres() {
        Assert.assertTrue(DatabaseConfig.getUrl().startsWith("jdbc:postgresql://"));
    }

    @Test
    public void debeValidarReporteValidoEInvalido() {
        Reporte reporteValido = new Reporte();
        reporteValido.setTitulo("Fuga de agua en la avenida principal");
        reporteValido.setDescripcion("El servicio presenta fuga y hay riesgo para vecinos.");
        reporteValido.setUbicacion("Colonia Centro, bloque 3");
        reporteValido.setPrioridad("ALTA");
        Assert.assertTrue(ReporteValidator.validate(reporteValido) == null);

        Reporte reporteInvalido = new Reporte();
        reporteInvalido.setTitulo(" ");
        reporteInvalido.setDescripcion("");
        reporteInvalido.setUbicacion(" ");
        reporteInvalido.setPrioridad("URGENTE");
        Assert.assertFalse(ReporteValidator.validate(reporteInvalido) == null);
    }

    @Test
    public void debeNormalizarPrioridadEnMinusculas() {
        Reporte reporte = new Reporte();
        reporte.setTitulo("Fuga de agua");
        reporte.setDescripcion("La tubería principal presenta filtraciones");
        reporte.setUbicacion("Barrio Centro");
        reporte.setPrioridad(" media ");

        Assert.assertTrue(ReporteValidator.validate(reporte) == null);
        Assert.assertEquals("MEDIA", reporte.getPrioridad());
    }
}
