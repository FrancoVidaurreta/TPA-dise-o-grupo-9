package ar.edu.utn.donatrack.donaciones.estados;

import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;

public class AsignacionRealizada extends EstadoDonacion {
    public AsignacionRealizada(DonacionSegmentada donacion) {
        super(donacion);
    }

    @Override
    public void planificarRuta() {
        this.donacion.cambiarEstado(new ListaParaEntregar(this.donacion));
    }

    @Override
    public void vencer() {
        this.donacion.cambiarEstado(new Vencida(this.donacion));
    }
}
