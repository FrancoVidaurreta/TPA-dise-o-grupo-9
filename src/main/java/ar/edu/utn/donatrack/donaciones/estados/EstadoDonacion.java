package ar.edu.utn.donatrack.donaciones.estados;

import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;

public abstract class EstadoDonacion {
    protected DonacionSegmentada donacion;

    public EstadoDonacion(DonacionSegmentada donacion) {
        this.donacion = donacion;

    }

    public void asignar() {
        throw new IllegalStateException("Transición no válida desde este estado.");
    }

    public void planificarRuta() {
        throw new IllegalStateException("Transición no válida desde este estado.");
    }

    public void iniciarTraslado() {
        throw new IllegalStateException("Transición no válida desde este estado.");
    }

    public void confirmarEntrega() {
        throw new IllegalStateException("Transición no válida desde este estado.");
    }

    public void fallarEntrega(String justificacion) {
        throw new IllegalStateException("Transición no válida desde este estado.");

    }

    public void vencer() {
        throw new IllegalStateException("Transición no válida desde este estado.");
    }
}
