package ar.edu.utn.donatrack.entidades;

import ar.edu.utn.donatrack.donaciones.Subcategoria;

public class NecesidadExtraordinaria extends Necesidad {

    public NecesidadExtraordinaria(Subcategoria subcategoria, String descripcion, double cantidadObjetivo) {
        super(subcategoria, descripcion, cantidadObjetivo);
    }

    @Override
    public boolean estaSatisfecha() {
        return getCantidadRecibida() >= cantidadObjetivo;
    }
}
