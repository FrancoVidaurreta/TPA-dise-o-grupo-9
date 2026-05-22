package ar.edu.utn.donatrack.donaciones.estados;

import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;

public class EnDeposito extends EstadoDonacion {
    public EnDeposito(DonacionSegmentada donacion) {
        super(donacion);
    }

    @Override
    public void asignar() {
        this.donacion.cambiarEstado(new AsignacionRealizada(this.donacion));
    }

    @Override
    public void vencer() {
        this.donacion.cambiarEstado(new Vencida(this.donacion));
    }
}
