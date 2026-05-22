package ar.edu.utn.donatrack.donaciones.estados;

import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;

public class ListaParaEntregar extends EstadoDonacion {
    public ListaParaEntregar(DonacionSegmentada donacion) {
        super(donacion);
    }

    @Override
    public void iniciarTraslado() {
        this.donacion.cambiarEstado(new EnTraslado(this.donacion));
    }

    @Override
    public void vencer() {
        this.donacion.cambiarEstado(new Vencida(this.donacion));
    }
}
