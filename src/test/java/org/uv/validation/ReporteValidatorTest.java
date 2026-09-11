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
}
