package ar.edu.utn.donatrack.donaciones.estados;

import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;

public class EntregaFallida extends EstadoDonacion {
    private String justificacion;

    public EntregaFallida(DonacionSegmentada donacion, String justificacion) {
        super(donacion);
        this.justificacion = justificacion;
    }

    public String getJustificacion() { return justificacion; }

    @Override
    public void asignar() {
        // Vuelve al depósito y puede reasignarse
        this.donacion.cambiarEstado(new AsignacionRealizada(this.donacion));
    }

    @Override
    public void vencer() {
        this.donacion.cambiarEstado(new Vencida(this.donacion));
    }
}
