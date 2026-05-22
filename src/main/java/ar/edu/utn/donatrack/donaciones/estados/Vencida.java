package ar.edu.utn.donatrack.donaciones.estados;

import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;

public class Vencida extends EstadoDonacion {
    public Vencida(DonacionSegmentada donacion) {
        super(donacion);
    }

    // Estado final, no transiciona a otros.
}
