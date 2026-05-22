package ar.edu.utn.donatrack.entidades;

import ar.edu.utn.donatrack.donaciones.Subcat;

public class NecesidadExtraordinaria extends Necesidad {

    public NecesidadExtraordinaria(Subcat subcat, String descripcion, double cantDeseada) {
        super(subcat, descripcion, cantDeseada);
    }

    @Override
    public boolean estaSatisfecha() {
        return getCantidadRecibida() >= cantDeseada;
    }
}
