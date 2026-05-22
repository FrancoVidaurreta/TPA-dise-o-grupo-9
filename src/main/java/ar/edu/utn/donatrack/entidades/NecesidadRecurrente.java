package ar.edu.utn.donatrack.entidades;

import ar.edu.utn.donatrack.donaciones.Subcategoria;
import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;

public class NecesidadRecurrente extends Necesidad {
    // Simplicamos la lógica periódica para la Entrega 1. 
    // En una implementación real verificaríamos por rango de fechas (ej. semanal).
    private String periodo; // Ej: "SEMANAL", "MENSUAL"

    public NecesidadRecurrente(Subcategoria subcategoria, String descripcion, double cantidadObjetivo, String periodo) {
        super(subcategoria, descripcion, cantidadObjetivo);
        this.periodo = periodo;
    }

    @Override
    public boolean estaSatisfecha() {
        // En una entrega futura aquí filtraríamos donacionesRecibidas por el periodo actual
        return getCantidadRecibida() >= cantidadObjetivo;
    }
}
