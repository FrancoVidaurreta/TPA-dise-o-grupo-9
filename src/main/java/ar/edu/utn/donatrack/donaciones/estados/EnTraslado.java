package ar.edu.utn.donatrack.donaciones.estados;

import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;

public class EnTraslado extends EstadoDonacion {
    public EnTraslado(DonacionSegmentada donacion) {
        super(donacion);
    }

    @Override
    public void confirmarEntrega() {
        this.donacion.cambiarEstado(new Entregada(this.donacion));
    }

    @Override

    public void fallarEntrega(String justificacion) {
        this.donacion.cambiarEstado(new EntregaFallida(this.donacion, justificacion));
    }
}
